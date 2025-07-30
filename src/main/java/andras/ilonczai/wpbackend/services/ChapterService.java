package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.Chapter.ChapterRequestDto;
import andras.ilonczai.wpbackend.dtos.Chapter.ChapterResponseDto;
import andras.ilonczai.wpbackend.entities.Chapter;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.ChapterMapper;
import andras.ilonczai.wpbackend.repositories.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;

    public ChapterResponseDto getChapter(Long chapterId){
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new AppException("No chapter found with this id: " + chapterId, HttpStatus.NOT_FOUND));

        ChapterResponseDto dto = chapterMapper.toChapterResponseDto(chapter);
        return dto;
    }

    public ChapterResponseDto updateChapter(Long chapterId, ChapterRequestDto req){
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new AppException("No chapter found with this id: " + chapterId, HttpStatus.NOT_FOUND));

        chapter.setTitle(req.title());
        chapter.setContent(req.content());

        chapter.setPublished(req.isPublished());
        chapter.setPublishDate(req.publishDate());

        chapter.setAuthorNotes(req.authorNotes());

        Chapter savedChapter = chapterRepository.save(chapter);
        return chapterMapper.toChapterResponseDto(savedChapter);
    }

    public ChapterResponseDto createNextChapter(Long chapterId){
        Chapter lastchapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new AppException("No chapter found with this id: " + chapterId, HttpStatus.NOT_FOUND));

        int nextOrder = chapterRepository.findMaxOrderByStoryId(lastchapter.getStory().getId()) + 1;
        String newTitle = "Untitled Part " + nextOrder;

        Chapter chapter = Chapter.builder()
                .title(newTitle)
                .content("Untitled content")
                .chapterOrder(nextOrder)
                .story(lastchapter.getStory())
                .isPublished(false)
                .publishDate(null)
                .views(0L)
                .votes(0L)
                .commentsCount(0)
                .authorNotes(null)
                .readTimeMinutes(1)
                .wordCount(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Chapter savedChapter = chapterRepository.save(chapter);
        return chapterMapper.toChapterResponseDto(savedChapter);
    }
}
