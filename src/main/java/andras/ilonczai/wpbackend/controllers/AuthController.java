package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dto.CredentialsDto;
import andras.ilonczai.wpbackend.dto.UserDto;
import andras.ilonczai.wpbackend.dtos.SignUpDto;
import andras.ilonczai.wpbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto)
    {
        UserDto user = userService.login(credentialsDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.register(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
