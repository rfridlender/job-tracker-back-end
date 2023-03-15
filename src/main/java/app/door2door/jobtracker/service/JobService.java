package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.JobRequest;
import app.door2door.jobtracker.dto.JobUpdateRequest;
import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.entity.*;
import app.door2door.jobtracker.mapper.UserDtoMapper;
import app.door2door.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserDtoMapper userDtoMapper;

//    public List<Job> test(Integer id) {
//        return jobRepository.findByContractorId(id);
//    }

    private UserDto getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDtoMapper.apply(principal);
    }

    public Job create(JobRequest request) {
        Job job = Job.builder()
                .address(request.getAddress())
                .status(Status.UPCOMING)
                .workLogs(new ArrayList<WorkLog>())
                .contractor(request.getContractor())
                .jobSiteAccess(request.getJobSiteAccess())
                .createdBy(getUser().name())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        return jobRepository.save(job);
    }

    public List<Job> index() { return jobRepository.findAll(); }

    public Job show(Integer jobId) {
        return jobRepository.findById(jobId).orElseThrow();
    }

    public Job update(Integer jobId, JobUpdateRequest request) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        job.setAddress(request.getAddress());
        job.setStatus(request.getStatus());
        job.setLockStatus(request.getLockStatus());
        job.setShelvingStatus(request.getShelvingStatus());
        job.setShowerStatus(request.getShowerStatus());
        job.setMirrorStatus(request.getMirrorStatus());
        job.setContractor(request.getContractor());
        job.setJobSiteAccess(request.getJobSiteAccess());
        return jobRepository.save(job);
    }

    public Job delete(Integer jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        job.getWorkLogs().clear();
        jobRepository.delete(job);
        return job;
    }

}
