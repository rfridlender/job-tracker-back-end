package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.LoginRequest;
import app.door2door.jobtracker.dto.AuthenticationResponse;
import app.door2door.jobtracker.dto.UserCreateRequest;
import app.door2door.jobtracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> signup(@RequestBody UserCreateRequest request) {
//        return ResponseEntity.ok(service.signup(request));
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

}
