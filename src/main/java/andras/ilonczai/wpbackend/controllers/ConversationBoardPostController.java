package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.ConversationBoardPost.ConversationBoardPostResponseDto;
import andras.ilonczai.wpbackend.dtos.ConversationBoardPost.CreateConversationBoardPostRequestDto;
import andras.ilonczai.wpbackend.services.ConversationBoardPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class ConversationBoardPostController {

    private final ConversationBoardPostService postService;

    @PostMapping("/{posterId}/posts")
    public ResponseEntity<ConversationBoardPostResponseDto> createPost(@PathVariable Long posterId, @RequestBody CreateConversationBoardPostRequestDto req) {
        return ResponseEntity.ok(postService.createPost(posterId, req));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<ConversationBoardPostResponseDto>> getPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsForUser(userId));
    }
}
