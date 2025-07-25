package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.*;
import andras.ilonczai.wpbackend.dtos.Auth.CredentialsDto;
import andras.ilonczai.wpbackend.dtos.Auth.SignUpDto;
import andras.ilonczai.wpbackend.dtos.Profile.AboutDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserStatsRepository userStatsRepository;
    private final CloudinaryService cloudinaryService;
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

        UserStats userStats = UserStats.builder()
                .user(user)
                .followerCount(0)
                .storyCount(0)
                .readCount(0)
                .build();

        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .description("")
                .joinedAt(LocalDateTime.now())
                .gender("")
                .website("")
                .location("")
                .imageUrl("")
                .build();

        userStatsRepository.save(userStats);
        userProfileRepository.save(userProfile);
        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        UserProfile profile = userProfileRepository.findById(id).orElse(null);
        UserStats stats = userStatsRepository.findById(id).orElse(null);

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .userProfileDto(profile != null ? userMapper.toUserProfileDto(profile) : null)
                .userStatsDto(stats != null ? userMapper.toUserStatsDto(stats) : null)
                .build();
    }

    public UserProfileDto updateUserProfile(Long id, AboutDto aboutDto){
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new AppException("UserProfile not found for userId: " + id, HttpStatus.NOT_FOUND));

        userProfile.setDescription(aboutDto.description());
        userProfile.setGender(aboutDto.gender());
        userProfile.setWebsite(aboutDto.website());
        userProfile.setLocation(aboutDto.location());

        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        return userMapper.toUserProfileDto(savedUserProfile);
    }

    public String uploadProfileImage(Long id, MultipartFile file){
        try {
            String imageUrl = cloudinaryService.uploadProfileImage(file);

            UserProfile profile = userProfileRepository.findById(id)
                    .orElseThrow(() -> new AppException("UserProfile not found for userId: " + id, HttpStatus.NOT_FOUND));

            profile.setImageUrl(imageUrl);
            userProfileRepository.save(profile);
            return imageUrl;
        }
        catch (IOException e) {
            throw new AppException("Image upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UserDto> searchUsers(String query) {
        List<User> users = userRepository.findByUserNameContainingIgnoreCase(query);
        return users.stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto findByUserName(String userName){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException("There is no user by this username", HttpStatus.NOT_FOUND));

        UserDto userDto = userMapper.toUserDto(user);

        UserProfile profile = userProfileRepository.findById(userDto.getId()).orElse(null);
        UserStats stats = userStatsRepository.findById(userDto.getId()).orElse(null);

        return UserDto.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .userProfileDto(profile != null ? userMapper.toUserProfileDto(profile) : null)
                .userStatsDto(stats != null ? userMapper.toUserStatsDto(stats) : null)
                .build();
    }
}