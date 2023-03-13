package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.JobRequest;
import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.entity.Job;
import app.door2door.jobtracker.entity.User;
import app.door2door.jobtracker.mapper.UserDtoMapper;
import app.door2door.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository repository;
    private final UserDtoMapper userDtoMapper;

    private UserDto getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDtoMapper.apply(principal);
    }

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
