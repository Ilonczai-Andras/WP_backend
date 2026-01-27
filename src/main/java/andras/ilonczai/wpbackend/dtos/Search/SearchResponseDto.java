package andras.ilonczai.wpbackend.dtos.Search;

import andras.ilonczai.wpbackend.dtos.Story.StoryResponseDto;
import andras.ilonczai.wpbackend.dtos.UserDto;
import org.springframework.data.domain.Page;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResponseDto {
    private Page<UserDto> users;
    private Page<StoryResponseDto> stories;
}