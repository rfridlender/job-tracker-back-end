package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.AuthenticationRequest;
import app.door2door.jobtracker.dto.AuthenticationResponse;
import app.door2door.jobtracker.dto.RegistrationRequest;
import app.door2door.jobtracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(service.signup(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

}
