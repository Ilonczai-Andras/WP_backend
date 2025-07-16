package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.FollowDto;
import andras.ilonczai.wpbackend.dtos.FollowResponseDto;
import andras.ilonczai.wpbackend.dtos.UserDto;
import andras.ilonczai.wpbackend.entities.Follow;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.FollowMapper;
import andras.ilonczai.wpbackend.repositories.FollowRepository;
import andras.ilonczai.wpbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowMapper followMapper;

    public void followUser(Long followerId, Long followedId) {
        User follower = userRepository.findById(followerId).orElseThrow();
        User followed = userRepository.findById(followedId).orElseThrow();

        if (followRepository.existsByFollowerAndFollowed(follower, followed)) {
            throw new IllegalStateException("Already following this user.");
        }

        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .followedAt(LocalDateTime.now())
                .build();
        followRepository.save(follow);
    }

    public FollowDto getFollowers(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("No user found with this id: " + userId, HttpStatus.NOT_FOUND));

        List<Follow> follows = followRepository.findByFollowed(user);

        List<FollowResponseDto> followers = follows.stream()
                .map(followMapper::toFollowersResponseDto)
                .toList();

        return FollowDto.builder()
                .followers(followers)
                .following(null)
                .build();
    }

    @Transactional
    public void unfollowUser(Long followerId, Long followedId){
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new AppException("Follower not found with this id: " + followerId, HttpStatus.NOT_FOUND));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new AppException("No following was found with this id: " + followedId, HttpStatus.NOT_FOUND));

        followRepository.deleteByFollowerAndFollowed(follower, followed);
    }

    public FollowDto getFollowing(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("No user found with this id: " + userId, HttpStatus.NOT_FOUND));

        List<Follow> follows = followRepository.findByFollower(user);
        System.out.println(follows);

        List<FollowResponseDto> following = follows.stream()
                .map(followMapper::toFollowingResponseDto)
                .toList();

        return FollowDto.builder()
                .followers(null)
                .following(following)
                .build();
    }
}
