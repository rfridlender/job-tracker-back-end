package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<UserDto> show(@PathVariable String email) {
        return ResponseEntity.ok(service.show(email));
    }

}
