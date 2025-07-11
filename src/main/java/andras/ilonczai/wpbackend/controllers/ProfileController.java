package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.UpdateDescriptionRequestDto;
import andras.ilonczai.wpbackend.dtos.UserDto;
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

    @PutMapping("/api/user-profiles/{id}/description")
    public ResponseEntity<?> updateDescription(@Valid @PathVariable Long id, @RequestBody UpdateDescriptionRequestDto updateDescriptionRequestDto){
        UserProfile userProfile = userService.updateDescription(id, updateDescriptionRequestDto);
        return ResponseEntity.ok(userProfile);
    }
}
