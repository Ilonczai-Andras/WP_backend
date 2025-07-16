package andras.ilonczai.wpbackend.dtos;

import andras.ilonczai.wpbackend.entities.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowDto {
    private List<FollowResponseDto> followers;
    private List<FollowResponseDto> following;
}
