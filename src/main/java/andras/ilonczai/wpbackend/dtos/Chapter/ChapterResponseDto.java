package andras.ilonczai.wpbackend.dtos.Chapter;

import andras.ilonczai.wpbackend.entities.enums.StoryStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterResponseDto {
    private Long id;
    private String title;
    private String content;
    private Integer chapterOrder;
    private StoryStatusEnum status;

    private boolean isPublished;
    private LocalDateTime publishDate;

    private Long views;
    private Long votes;
    private Integer commentsCount;

    private String authorNotes;
    private Integer readTimeMinutes;
    private Integer wordCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
