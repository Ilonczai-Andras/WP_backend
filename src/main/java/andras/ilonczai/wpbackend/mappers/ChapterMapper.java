package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.Chapter.ChapterResponseDto;
import andras.ilonczai.wpbackend.entities.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    ChapterResponseDto toChapterResponseDto(Chapter chapter);
}
