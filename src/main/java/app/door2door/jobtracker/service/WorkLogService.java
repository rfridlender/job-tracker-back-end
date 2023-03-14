package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.dto.WorkLogRequest;
import app.door2door.jobtracker.entity.*;
import app.door2door.jobtracker.mapper.UserDtoMapper;
import app.door2door.jobtracker.repository.JobRepository;
import app.door2door.jobtracker.repository.WorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class WorkLogService {

    private final JobRepository jobRepository;
    private final WorkLogRepository workLogRepository;
    private final UserDtoMapper userDtoMapper;

    private UserDto getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDtoMapper.apply(principal);
    }

    public Job create(Integer jobId, WorkLogRequest request) {
        WorkLog workLog = WorkLog.builder()
                .submittedAt(new Timestamp(System.currentTimeMillis()))
                .employeeName(getUser().name())
                .category(request.getCategory())
                .workDate(request.getWorkDate())
                .startTime(new Time(request.getStartTime()))
                .endTime(new Time(request.getEndTime()))
                .workCompleted(request.getWorkCompleted())
                .isCompleted(request.isCompleted())
                .incompleteItems(request.getIncompleteItems())
//                .job(request.getJob())
                .build();
//        WorkLog savedLog = workLogRepository.save(workLog);
        Job job = jobRepository.findById(jobId).orElseThrow();
        job.getWorkLogs().add(workLog);
        Job savedJob = jobRepository.save(job);
        return savedJob;
    }

    public Job update(Integer jobId, Integer workLogId, WorkLogRequest request) {
    }

}

//    private Integer id;
//    private java.sql.Timestamp submittedAt;
//    private String employeeName;
//    private Category category;
//    private Date workDate;
//    private Time startTime;
//    private Time endTime;
//    private String workCompleted;
//    private boolean isCompleted;
//    private String incompleteItems;
//    private Job job;
