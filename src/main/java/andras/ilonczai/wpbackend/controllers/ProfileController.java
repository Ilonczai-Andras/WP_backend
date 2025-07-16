package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.AboutDto;
import andras.ilonczai.wpbackend.dtos.UserDto;
import andras.ilonczai.wpbackend.dtos.UserProfileDto;
import andras.ilonczai.wpbackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class ProfileController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String username){
        UserDto user = userService.findByUserName(username);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam String query){
        List<UserDto> users = userService.searchUsers(query);
        return  ResponseEntity.ok(users);
    }

    @PutMapping("/about/{id}")
    public ResponseEntity<UserProfileDto> updateUserProfile(@Valid @PathVariable Long id, @RequestBody AboutDto aboutDto){
        UserProfileDto userProfileDto = userService.updateUserProfile(id, aboutDto);
        return ResponseEntity.ok(userProfileDto);
    }

    @PostMapping("/profile-image/{id}")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String imageUrl = userService.uploadProfileImage(id, file);
        return ResponseEntity.ok(imageUrl);
    }
}
