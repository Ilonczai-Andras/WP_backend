package andras.ilonczai.wpbackend.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserStatsDto {
    private int followerCount;
    private int storyCount;
    private int readCount;
}
