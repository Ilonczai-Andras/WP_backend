package andras.ilonczai.wpbackend.dtos.Follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String imgUrl;
}
