package andras.ilonczai.wpbackend.dtos.Readinglists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadingListResponseDto {
    private Long id;
    private String name;
    private boolean isPrivate;
    private Long ownerId;
    private String ownerUsername;
    private int storyCount;
}
