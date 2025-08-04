package andras.ilonczai.wpbackend.dtos.Readinglists;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStoryToListRequestDto {
    private List<Long> listIds;
    private Long storyId;
}
