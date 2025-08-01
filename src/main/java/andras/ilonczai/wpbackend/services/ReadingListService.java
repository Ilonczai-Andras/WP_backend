package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.Readinglists.AddStoryToListRequestDto;
import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListItemResponseDto;
import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListRequestDto;
import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListResponseDto;
import andras.ilonczai.wpbackend.entities.ReadingList;
import andras.ilonczai.wpbackend.entities.ReadingListItem;
import andras.ilonczai.wpbackend.entities.Story;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.ReadingListMapper;
import andras.ilonczai.wpbackend.repositories.ReadingListItemRepository;
import andras.ilonczai.wpbackend.repositories.ReadingListRepository;
import andras.ilonczai.wpbackend.repositories.StoryRepository;
import andras.ilonczai.wpbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingListService {

    private final ReadingListRepository readingListRepository;
    private final ReadingListItemRepository readingListItemRepository;
    private final StoryRepository storyRepository;
    private final ReadingListMapper readingListMapper;
    private final UserRepository userRepository;

    public ReadingListResponseDto createReadingList(Long ownerId, ReadingListRequestDto req) {

        User user = userRepository.findById(ownerId)
                .orElseThrow(() -> new AppException("User not found for userId: " + ownerId, HttpStatus.NOT_FOUND));

        ReadingList readingList = ReadingList.builder()
                .name(req.getName())
                .isPrivate(req.isPrivate())
                .owner(user)
                .build();
        ReadingList savedReadingList = readingListRepository.save(readingList);
        return readingListMapper.ReadingListToDto(savedReadingList);
    }

    public void addStoryToList(AddStoryToListRequestDto req) {

        ReadingList readingList = readingListRepository.findById(req.getListId())
                .orElseThrow(() -> new AppException("Reading list not found with listId: " + req.getListId(), HttpStatus.NOT_FOUND));

        Story story = storyRepository.findById(req.getStoryId())
                .orElseThrow(() -> new AppException("Story not found with storyId: " + req.getStoryId(), HttpStatus.NOT_FOUND));


        ReadingListItem readingListItem = ReadingListItem.builder()
                .readingList(readingList)
                .story(story)
                .build();
        ReadingListItem savedReadingListItem = readingListItemRepository.save(readingListItem);
    }

    public List<ReadingListResponseDto> getUserLists(Long userId) {
        List<ReadingList> list = readingListRepository.findByOwner_Id(userId);

        List<ReadingListResponseDto> readingListResponseDtos = list.stream()
                .map( readingList -> {
                    ReadingListResponseDto dto = readingListMapper.ReadingListToDto(readingList);
                    return dto;
                })
                .toList();

        return readingListResponseDtos;
    }

    public List<ReadingListItemResponseDto> getListItems(Long listId) {
        List<ReadingListItem> readingListItems = readingListItemRepository.findByReadingList_Id(listId);

        List<ReadingListItemResponseDto> readingListItemResponseDtos = readingListItems.stream()
                .map( readingListItem -> {
                    ReadingListItemResponseDto dto = readingListMapper.ReadingListItemToDto(readingListItem);
                    return dto;
                })
                .toList();
        return readingListItemResponseDtos;
    }
}
