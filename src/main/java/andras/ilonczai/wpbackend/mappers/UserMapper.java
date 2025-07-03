package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dto.UserDto;
import andras.ilonczai.wpbackend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
}
