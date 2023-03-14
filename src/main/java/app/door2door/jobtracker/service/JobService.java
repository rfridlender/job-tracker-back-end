package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.JobRequest;
import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.entity.Contractor;
import app.door2door.jobtracker.entity.Job;
import app.door2door.jobtracker.entity.Status;
import app.door2door.jobtracker.entity.User;
import app.door2door.jobtracker.mapper.UserDtoMapper;
import app.door2door.jobtracker.repository.ContractorRepository;
import app.door2door.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final ContractorRepository contractorRepository;
    private final UserDtoMapper userDtoMapper;

    private UserDto getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDtoMapper.apply(principal);
    }

    public Job create(JobRequest request) {
        Contractor contractor = contractorRepository.findById(request.getContractorId())
                .orElseThrow();
        Job job = Job.builder()
                .address(request.getAddress())
                .status(Status.UPCOMING)
                .contractor(contractor)
                .createdBy(getUser().name())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        jobRepository.save(job);
        return job;
    }
//    private Integer id;
//    private String address;
//    @Enumerated(EnumType.STRING)
//    private Status status;
//    private String lockStatus;
//    private String shelvingStatus;
//    private String showerStatus;
//    private String mirrorStatus;
//    private List<WorkLog> workLogs = new ArrayList<>();
//    private Contractor builder;
//    private String createdBy;
//    private java.sql.Timestamp createdAt;

    public List<Job> index() {
        return jobRepository.findAll();
    }
}
