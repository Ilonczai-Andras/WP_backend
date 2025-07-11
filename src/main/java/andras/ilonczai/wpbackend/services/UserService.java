package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.*;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.entities.UserProfile;
import andras.ilonczai.wpbackend.entities.UserStats;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.UserMapper;
import andras.ilonczai.wpbackend.repositories.UserProfileRepository;
import andras.ilonczai.wpbackend.repositories.UserRepository;
import andras.ilonczai.wpbackend.repositories.UserStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserStatsRepository userStatsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto){

        User user = userRepository.findByUserName(credentialsDto.userName())
                .orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())){
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);

    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<User> optionalUser = userRepository.findByUserName(signUpDto.userName());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile profile = userProfileRepository.findById(id).orElse(null);
        UserStats stats = userStatsRepository.findById(id).orElse(null);

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .profile(profile != null ? UserProfileDto.builder()
                        .description(profile.getDescription())
                        .joinedAt(profile.getJoinedAt())
                        .build() : null)
                .stats(stats != null ? UserStatsDto.builder()
                        .followerCount(stats.getFollowerCount())
                        .storyCount(stats.getStoryCount())
                        .readCount(stats.getReadCount())
                        .build() : null)
                .build();
    }
}