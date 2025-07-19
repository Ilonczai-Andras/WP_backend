package andras.ilonczai.wpbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationBoardPostResponseDto {
    private Long id;
    private String content;
    private String posterUsername;
    private String ownerUsername;
    private LocalDateTime postedAt;
    private Long parentId;
    private boolean isReply;
    private List<ConversationBoardPostResponseDto> replies;
}
