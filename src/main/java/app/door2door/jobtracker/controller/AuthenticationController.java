package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.ChangePasswordRequest;
import app.door2door.jobtracker.dto.LoginRequest;
import app.door2door.jobtracker.dto.AuthenticationResponse;
import app.door2door.jobtracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<AuthenticationResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(service.changePassword(request));
    }

}
