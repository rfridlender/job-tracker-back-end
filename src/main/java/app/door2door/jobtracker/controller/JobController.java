package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.JobRequest;
import app.door2door.jobtracker.entity.Job;
import app.door2door.jobtracker.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService service;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Job> create(@RequestBody JobRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<Job>> index() {
        return ResponseEntity.ok(service.index());
    }

}
