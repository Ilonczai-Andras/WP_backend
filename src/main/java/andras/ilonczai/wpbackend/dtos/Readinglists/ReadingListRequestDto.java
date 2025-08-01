package andras.ilonczai.wpbackend.dtos.Readinglists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadingListRequestDto {
    private String name;
    private boolean isPrivate;
}