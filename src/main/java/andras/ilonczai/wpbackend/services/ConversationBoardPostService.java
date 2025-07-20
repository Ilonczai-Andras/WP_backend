package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.ConversationBoardPost.ConversationBoardPostResponseDto;
import andras.ilonczai.wpbackend.dtos.ConversationBoardPost.CreateConversationBoardPostRequestDto;
import andras.ilonczai.wpbackend.entities.ConversationBoardPost;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.ConversationBoardPostMapper;
import andras.ilonczai.wpbackend.repositories.ConversationBoardPostRepository;
import andras.ilonczai.wpbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationBoardPostService {

    private final ConversationBoardPostRepository conversationBoardPostRepository;
    private final UserRepository userRepository;
    private final ConversationBoardPostMapper conversationBoardPostMapper;

    public ConversationBoardPostResponseDto createPost(Long posterId, CreateConversationBoardPostRequestDto req) {
        User owner = userRepository.findById(req.ownerId())
                .orElseThrow(() -> new AppException("No user found with this id: " + req.ownerId(), HttpStatus.NOT_FOUND));

        User poster = userRepository.findById(posterId)
                .orElseThrow(() -> new AppException("Poster user with this id not found: " + posterId, HttpStatus.NOT_FOUND));

        Set<User> mentioned = extractMentions(req.content());

        ConversationBoardPost post =  ConversationBoardPost.builder()
                .owner(owner)
                .poster(poster)
                .content(req.content())
                .mentionedUsers(mentioned)
                .build();

        if (req.parentPostId() != null) {
            ConversationBoardPost parent = conversationBoardPostRepository.findById(req.parentPostId())
                    .orElseThrow(() -> new RuntimeException("Parent post not found"));

            // Limit replies to 1 level only
            if (parent.getParent() != null) {
                throw new IllegalArgumentException("Replies to replies are not allowed.");
            }

            post.setParent(parent);
        }

        post = conversationBoardPostRepository.save(post);
        return conversationBoardPostMapper.toConversationBoardPostResponseDto(post);
    }

    private Set<User> extractMentions(String content) {
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(content);
        Set<User> mentions = new HashSet<>();

        while (matcher.find()) {
            String username = matcher.group(1);
            userRepository.findByUserName(username).ifPresent(mentions::add);
        }

        return mentions;
    }

    public List<ConversationBoardPostResponseDto> getPostsForUser(Long userId) {
        List<ConversationBoardPost> posts = conversationBoardPostRepository.findByOwnerIdAndParentIsNullOrderByPostedAtDesc(userId);
        return posts.stream()
                .map(conversationBoardPostMapper::toConversationBoardPostResponseDto)
                .collect(Collectors.toList());
    }
}
