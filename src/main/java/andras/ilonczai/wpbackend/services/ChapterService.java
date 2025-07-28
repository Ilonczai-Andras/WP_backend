package andras.ilonczai.wpbackend.services;

import andras.ilonczai.wpbackend.dtos.Chapter.ChapterResponseDto;
import andras.ilonczai.wpbackend.entities.Chapter;
import andras.ilonczai.wpbackend.exceptions.AppException;
import andras.ilonczai.wpbackend.mappers.ChapterMapper;
import andras.ilonczai.wpbackend.repositories.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
}
