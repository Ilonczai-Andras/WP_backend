package andras.ilonczai.wpbackend.dtos.Readinglists;

import andras.ilonczai.wpbackend.entities.ReadingListItem;
import andras.ilonczai.wpbackend.entities.enums.ReadingListEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<ReadingListItemResponseDto> items;
    private ReadingListEnum readingListType;
}
