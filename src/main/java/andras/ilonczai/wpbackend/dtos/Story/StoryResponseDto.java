package andras.ilonczai.wpbackend.dtos.Story;

import andras.ilonczai.wpbackend.dtos.Chapter.ChapterResponseDto;
import andras.ilonczai.wpbackend.entities.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoryResponseDto {
    Long id;
    String title;
    String description;
    List<String> mainCharacters;
    CategoryEnum category;
    List<String> tags;
    TargetAudienceEnum targetAudience;
    LanguageEnum language;
    CopyrightLicenseEnum copyright;
    boolean mature;
    List<ChapterResponseDto> chapters;
    String coverImageUrl;
    String authorUsername;
    Long authorId;
    StoryStatusEnum status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
