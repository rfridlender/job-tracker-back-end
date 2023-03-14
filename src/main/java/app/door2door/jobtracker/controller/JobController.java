package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.JobRequest;
import app.door2door.jobtracker.dto.JobUpdateRequest;
import app.door2door.jobtracker.entity.*;
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

    private final JobService jobService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<Job>> index() {
        return ResponseEntity.ok(jobService.index());
    }

    @GetMapping("/{jobId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Job> show(@PathVariable Integer jobId) {
        return ResponseEntity.ok(jobService.show(jobId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Job> create(@RequestBody JobRequest request) {
        return ResponseEntity.ok(jobService.create(request));
    }

    @PutMapping("/{jobId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Job> update(@PathVariable Integer jobId, @RequestBody JobUpdateRequest request) {
        return ResponseEntity.ok(jobService.update(jobId, request));
    }

    @DeleteMapping("/{jobId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Job> delete(@PathVariable Integer jobId) {
        return ResponseEntity.ok(jobService.delete(jobId));
    }

}
