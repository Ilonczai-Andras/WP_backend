package andras.ilonczai.wpbackend.dtos.Readinglists;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStoryToListRequestDto {
    private Long listId;
    private Long storyId;
}
