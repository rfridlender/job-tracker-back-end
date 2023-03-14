package app.door2door.jobtracker.controller;

import app.door2door.jobtracker.dto.ContractorRequest;
import app.door2door.jobtracker.entity.*;
import app.door2door.jobtracker.service.ContractorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/builders")
@RequiredArgsConstructor
public class ContractorController {

    private final ContractorService service;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<Contractor>> index() {
        return ResponseEntity.ok(service.index());
    }

    @GetMapping("/{contractorId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Contractor> show(@PathVariable Integer contractorId) {
        return ResponseEntity.ok(service.show(contractorId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Contractor> create(@RequestBody ContractorRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @DeleteMapping("/{contractorId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Contractor> delete(@PathVariable Integer contractorId) {
        return ResponseEntity.ok(service.delete(contractorId));
    }

}
