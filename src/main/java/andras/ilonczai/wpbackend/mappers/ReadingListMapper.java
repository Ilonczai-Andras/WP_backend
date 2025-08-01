package andras.ilonczai.wpbackend.mappers;

import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListItemResponseDto;
import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListResponseDto;
import andras.ilonczai.wpbackend.entities.ReadingList;
import andras.ilonczai.wpbackend.entities.ReadingListItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReadingListMapper {

    @Mapping(target = "ownerId", expression = "java(list.getOwner() != null ? list.getOwner().getId() : null)")
    @Mapping(target = "ownerUsername", expression = "java(list.getOwner() != null ? list.getOwner().getUserName() : null)")
    ReadingListResponseDto ReadingListToDto (ReadingList list);

    @Mapping(target = "storyId", expression = "java(item.getStory() != null ? item.getStory().getId() : null)")
    @Mapping(target = "title", expression = "java(item.getStory() != null ? item.getStory().getTitle() : null)")
    @Mapping(target = "coverImageUrl", expression = "java(item.getStory() != null ? item.getStory().getCoverImageUrl() : null)")
    @Mapping(target = "authorUsername", expression = "java(item.getStory() != null ? item.getStory().getAuthor().getUserName() : null)")
    @Mapping(target = "description", expression = "java(item.getStory() != null ? item.getStory().getDescription() : null)")
    ReadingListItemResponseDto ReadingListItemToDto (ReadingListItem item);
}
