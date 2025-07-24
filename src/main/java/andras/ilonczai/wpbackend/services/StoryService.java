package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.Story.StoryRequestDto;
import andras.ilonczai.wpbackend.dtos.Story.StoryResponseDto;
import andras.ilonczai.wpbackend.entities.Story;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.entities.UserProfile;
import andras.ilonczai.wpbackend.entities.enums.StoryStatusEnum;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.StoryMapper;
import andras.ilonczai.wpbackend.repositories.StoryRepository;
import andras.ilonczai.wpbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StoryService {

        private final UserRepository userRepository;
        private final StoryRepository storyRepository;
        private final StoryMapper storyMapper;
        private final CloudinaryService cloudinaryService;

        public StoryResponseDto createStory(Long userId, StoryRequestDto req, MultipartFile file){

            String imageUrl = "";
            User author = userRepository.findById(userId)
                    .orElseThrow(() -> new AppException("No user found with this id: " + userId, HttpStatus.NOT_FOUND));

            try {
                imageUrl = cloudinaryService.uploadProfileImage(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Story story = Story.builder()
                    .title(req.title())
                    .description(req.description())
                    .mainCharacters(req.mainCharacters())
                    .category(req.category())
                    .tags(req.tags())
                    .targetAudience(req.targetAudience())
                    .language(req.language())
                    .copyright(req.copyright())
                    .mature(req.mature())
                    .coverImageUrl(imageUrl)
                    .author(author)
                    .status(StoryStatusEnum.DRAFT)
                    .build();

            Story savedStory = storyRepository.save(story);
            return storyMapper.toStoryResponseDto(savedStory);
        }
}
