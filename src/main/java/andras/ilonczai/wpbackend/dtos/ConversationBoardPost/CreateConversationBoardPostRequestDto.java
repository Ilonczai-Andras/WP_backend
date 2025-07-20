package andras.ilonczai.wpbackend.dtos.ConversationBoardPost;

public record CreateConversationBoardPostRequestDto(Long ownerId, String content, Long parentPostId) {
}
