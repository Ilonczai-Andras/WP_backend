package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.Search.SearchResponseDto;
import andras.ilonczai.wpbackend.dtos.UserDto;
import andras.ilonczai.wpbackend.dtos.Story.StoryResponseDto;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.entities.Story;
import andras.ilonczai.wpbackend.entities.UserStats;
import andras.ilonczai.wpbackend.mappers.StoryMapper;
import andras.ilonczai.wpbackend.mappers.UserMapper;
import andras.ilonczai.wpbackend.repositories.UserRepository;
import andras.ilonczai.wpbackend.repositories.StoryRepository;
import andras.ilonczai.wpbackend.repositories.UserStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final StoryRepository storyRepository;
    private final UserStatsRepository userStatsRepository;

    private final StoryMapper storyMapper;
    private final UserMapper userMapper;

    public SearchResponseDto search(String query, Pageable pageable) {

        CompletableFuture<Page<User>> usersFuture = CompletableFuture.supplyAsync(() ->
                userRepository.findByUserNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        query, query, query, pageable
                )
        );

        CompletableFuture<Page<Story>> storiesFuture = CompletableFuture.supplyAsync(() ->
                storyRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        query, query, pageable
                )
        );

        return CompletableFuture.allOf(usersFuture, storiesFuture)
                .thenApply(voidResult -> {
                    Page<User> userPage = usersFuture.join();
                    Page<Story> storyPage = storiesFuture.join();

                    List<Long> userIds = userPage.getContent().stream()
                            .map(User::getId)
                            .toList();

                    Map<Long, UserStats> statsMap = userStatsRepository.findAllById(userIds).stream()
                            .collect(Collectors.toMap(UserStats::getId, stats -> stats));

                    Page<UserDto> userDtos = userPage.map(user -> {
                        UserDto dto = userMapper.toUserDto(user);

                        Optional.ofNullable(statsMap.get(user.getId()))
                                .ifPresent(stats -> dto.setUserStatsDto(userMapper.toUserStatsDto(stats)));

                        return dto;
                    });

                    return SearchResponseDto.builder()
                            .users(userDtos)
                            .stories(storyPage.map(storyMapper::toStorySummaryDto))
                            .build();
                }).join();
    }
}