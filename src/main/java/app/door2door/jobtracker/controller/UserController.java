package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.UserCreateRequest;
import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.dto.UserUpdateRequest;
import app.door2door.jobtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> index() { return ResponseEntity.ok(service.index()); }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> create(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> update(@PathVariable Integer userId, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(service.update(userId, request));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> delete(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.delete(userId));
    }

}
