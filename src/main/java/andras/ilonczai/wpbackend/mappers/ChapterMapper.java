package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.Chapter.ChapterResponseDto;
import andras.ilonczai.wpbackend.entities.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    @Mapping(target = "status", expression = "java(chapter.getStory() != null ? chapter.getStory().getStatus() : null)")
    ChapterResponseDto toChapterResponseDto(Chapter chapter);
}
