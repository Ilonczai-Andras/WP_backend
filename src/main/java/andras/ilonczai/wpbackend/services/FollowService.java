package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.Follow.FollowDto;
import andras.ilonczai.wpbackend.dtos.Follow.FollowResponseDto;
import andras.ilonczai.wpbackend.entities.Follow;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.entities.UserProfile;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.FollowMapper;
import andras.ilonczai.wpbackend.repositories.FollowRepository;
import andras.ilonczai.wpbackend.repositories.UserProfileRepository;
import andras.ilonczai.wpbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
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
                .map(follow -> {
                    FollowResponseDto dto = followMapper.toFollowersResponseDto(follow);
                    UserProfile profile = userProfileRepository.findById(dto.getId()).orElse(null);
                    dto.setImgUrl(profile.getImageUrl());
                return dto;
                })
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

        List<FollowResponseDto> following = follows.stream()
                .map(follow -> {
                    FollowResponseDto dto = followMapper.toFollowingResponseDto(follow);
                    UserProfile profile = userProfileRepository.findById(dto.getId()).orElse(null);
                    dto.setImgUrl(profile.getImageUrl());
                return dto;
                })
                .toList();

        return FollowDto.builder()
                .followers(null)
                .following(following)
                .build();
    }
}
