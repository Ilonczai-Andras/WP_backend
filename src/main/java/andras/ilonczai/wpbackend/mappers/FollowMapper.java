package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.Follow.FollowResponseDto;
import andras.ilonczai.wpbackend.entities.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    @Mappings({
            @Mapping(source = "follower.id", target = "id"),
            @Mapping(source = "follower.firstName", target = "firstName"),
            @Mapping(source = "follower.lastName", target = "lastName"),
            @Mapping(source = "follower.userName", target = "userName")
    })
    FollowResponseDto toFollowersResponseDto (Follow follow);

    @Mappings({
            @Mapping(source = "followed.id", target = "id"),
            @Mapping(source = "followed.firstName", target = "firstName"),
            @Mapping(source = "followed.lastName", target = "lastName"),
            @Mapping(source = "followed.userName", target = "userName")
    })
    FollowResponseDto toFollowingResponseDto (Follow follow);
}
