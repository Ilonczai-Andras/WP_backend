package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.UserDto;
import andras.ilonczai.wpbackend.dtos.SignUpDto;
import andras.ilonczai.wpbackend.dtos.UserProfileDto;
import andras.ilonczai.wpbackend.dtos.UserStatsDto;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.entities.UserProfile;
import andras.ilonczai.wpbackend.entities.UserStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

    UserProfileDto toUserProfileDto(UserProfile userProfile);

    UserStatsDto toUserStatsDto(UserStats userStats);
}
