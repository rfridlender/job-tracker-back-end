package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.WorkLogRequest;
import app.door2door.jobtracker.entity.Job;
import app.door2door.jobtracker.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs/{jobId}/logs")
@RequiredArgsConstructor
public class WorkLogController {

    private final WorkLogService workLogService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Job> create(@PathVariable Integer jobId, @RequestBody WorkLogRequest request) {
        return ResponseEntity.ok(workLogService.create(jobId, request));
    }

    @PutMapping("/{workLogId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Job> update(
            @PathVariable Integer jobId, @PathVariable Integer workLogId, @RequestBody WorkLogRequest request
    ) {
        return ResponseEntity.ok(workLogService.update(jobId, workLogId, request));
    }

    @DeleteMapping("/{workLogId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Job> delete(@PathVariable Integer jobId, @PathVariable Integer workLogId) {
        return ResponseEntity.ok(workLogService.delete(jobId, workLogId));
    }

}
