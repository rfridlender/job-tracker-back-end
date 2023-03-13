package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.JobRequest;
import app.door2door.jobtracker.entity.Job;
import app.door2door.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository repository;

    public Job create(JobRequest request) {
        Job job = Job.builder()
                .address(request.getAddress())
                .build();
        repository.save(job);
        return job;
    }

    public List<Job> index() {
        return repository.findAll();
    }
}
