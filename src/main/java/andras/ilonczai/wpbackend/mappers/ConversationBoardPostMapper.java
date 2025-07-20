package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.ConversationBoardPost.ConversationBoardPostResponseDto;
import andras.ilonczai.wpbackend.entities.ConversationBoardPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConversationBoardPostMapper {

    @Mapping(source = "poster.userName", target = "posterUsername")
    @Mapping(source = "owner.userName", target = "ownerUsername")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(target = "isReply", expression = "java(conversationBoardPost.getParent() != null)")
    ConversationBoardPostResponseDto toConversationBoardPostResponseDto (ConversationBoardPost conversationBoardPost);
}
