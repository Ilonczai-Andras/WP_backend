package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.Story.StoryRequestDto;
import andras.ilonczai.wpbackend.dtos.Story.StoryResponseDto;
import andras.ilonczai.wpbackend.services.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @PostMapping(value = "/{userId}/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StoryResponseDto> createStory(
            @PathVariable Long userId,
            @RequestPart("req") StoryRequestDto req,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        return ResponseEntity.ok(storyService.createStory(userId, req, file));
    }

    @GetMapping("/{authorId}/stories")
    public ResponseEntity<List<StoryResponseDto>> getStories(@PathVariable Long authorId){
        return ResponseEntity.ok(storyService.getStories(authorId));
    }

    @GetMapping("/{storyId}/story")
    public ResponseEntity<StoryResponseDto> getStory(@PathVariable Long storyId){
        return ResponseEntity.ok(storyService.getStory(storyId));
    }
}
