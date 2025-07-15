package andras.ilonczai.wpbackend.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserProfileDto {
    private String description;
    private LocalDateTime joinedAt;
    private String gender;
    private String website;
    private String location;
    private String imageUrl;
}
