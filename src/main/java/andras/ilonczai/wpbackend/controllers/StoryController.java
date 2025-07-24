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
            @RequestPart("file") MultipartFile file) {

        return ResponseEntity.ok(storyService.createStory(userId, req, file));
    }
}
