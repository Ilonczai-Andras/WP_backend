package andras.ilonczai.wpbackend.dtos.Chapter;

import andras.ilonczai.wpbackend.entities.enums.StoryStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
