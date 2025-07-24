package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.Story.StoryResponseDto;
import andras.ilonczai.wpbackend.entities.Story;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoryMapper {

    StoryResponseDto toStoryResponseDto(Story story);
}
