package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.AboutDto;
import andras.ilonczai.wpbackend.dtos.UserDto;
import andras.ilonczai.wpbackend.dtos.UserProfileDto;
import andras.ilonczai.wpbackend.entities.UserProfile;
import andras.ilonczai.wpbackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/api/user/about/{id}")
    public ResponseEntity<UserProfileDto> updateUserProfile(@Valid @PathVariable Long id, @RequestBody AboutDto aboutDto){
        UserProfileDto userProfileDto = userService.updateUserProfile(id, aboutDto);
        return ResponseEntity.ok(userProfileDto);
    }
}
