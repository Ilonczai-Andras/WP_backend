package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.Story.StoryResponseDto;
import andras.ilonczai.wpbackend.entities.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoryMapper {

    //GET /api/stories/{id}
    @Mapping(target = "authorUsername", expression = "java(story.getAuthor().getUserName())")
    @Mapping(target = "authorId", expression = "java(story.getAuthor().getId())")
    @Mapping(target = "coverImageUrl", expression = "java(story.getCoverImageUrl())")
    StoryResponseDto toStoryResponseDto(Story story);

    //GET /api/search
    @Mapping(target = "authorUsername", expression = "java(story.getAuthor().getUserName())")
    @Mapping(target = "authorId", expression = "java(story.getAuthor().getId())")
    @Mapping(target = "coverImageUrl", expression = "java(story.getCoverImageUrl())")
    @Mapping(target = "chapters", ignore = true)
    StoryResponseDto toStorySummaryDto(Story story);
}
