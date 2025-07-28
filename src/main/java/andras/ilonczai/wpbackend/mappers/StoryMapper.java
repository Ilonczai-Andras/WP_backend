package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.Story.StoryResponseDto;
import andras.ilonczai.wpbackend.entities.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoryMapper {

    @Mapping(target = "authorUsername", expression = "java(story.getAuthor().getUserName())")
    @Mapping(target = "coverImageUrl", expression = "java(story.getCoverImageUrl())")
    StoryResponseDto toStoryResponseDto(Story story);
}
