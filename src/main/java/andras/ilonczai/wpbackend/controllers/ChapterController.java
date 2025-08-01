package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.Chapter.ChapterRequestDto;
import andras.ilonczai.wpbackend.dtos.Chapter.ChapterResponseDto;
import andras.ilonczai.wpbackend.services.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("/{chapterId}/chapter")
    public ResponseEntity<ChapterResponseDto> getChapter(@PathVariable Long chapterId){
        return  ResponseEntity.ok(chapterService.getChapter(chapterId));
    }

    @PutMapping(value = "/{chapterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ChapterResponseDto> updateChapter(
            @PathVariable Long chapterId,
            @RequestPart("req") ChapterRequestDto req,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return ResponseEntity.ok(chapterService.updateChapter(chapterId, req, file));
    }

    @PostMapping("/next-chapter/{chapterId}")
    public ResponseEntity<ChapterResponseDto> createNextChapter(@Valid @PathVariable Long chapterId){
        return ResponseEntity.ok(chapterService.createNextChapter(chapterId));
    }
}
