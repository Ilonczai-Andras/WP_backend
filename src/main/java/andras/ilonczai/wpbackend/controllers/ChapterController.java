package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.Chapter.ChapterResponseDto;
import andras.ilonczai.wpbackend.services.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("{chapterId}/chapter")
    public ResponseEntity<ChapterResponseDto> getChapter(@PathVariable Long chapterId){
        return  ResponseEntity.ok(chapterService.getChapter(chapterId));
    }
}
