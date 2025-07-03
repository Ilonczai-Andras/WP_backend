package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dto.UserDto;
import andras.ilonczai.wpbackend.dtos.SignUpDto;
import andras.ilonczai.wpbackend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}
