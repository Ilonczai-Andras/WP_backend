package andras.ilonczai.wpbackend.dtos;

public record CreateConversationBoardPostRequestDto(Long ownerId, String content, Long parentPostId) {
}
