package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.FollowDto;
import andras.ilonczai.wpbackend.entities.User;
import andras.ilonczai.wpbackend.services.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<String> followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        followService.followUser(followerId, followedId);
        return ResponseEntity.ok("Followed successfully.");
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<FollowDto> getFollowers(@PathVariable Long userId) {
        FollowDto followers = followService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<FollowDto> getFollowing(@PathVariable Long userId) {
        FollowDto following = followService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }

    @DeleteMapping("/{followerId}/unfollow/{followedId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        followService.unfollowUser(followerId, followedId);
        return ResponseEntity.ok("Unfollowed successfully.");
    }
}
