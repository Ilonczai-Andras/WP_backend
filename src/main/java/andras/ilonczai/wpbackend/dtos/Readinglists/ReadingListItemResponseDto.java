package andras.ilonczai.wpbackend.dtos.Readinglists;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadingListItemResponseDto {
    private Long storyId;
    private String title;
    private String coverImageUrl;
    private String authorUsername;
    private String description;
    private String addedAt;
}
