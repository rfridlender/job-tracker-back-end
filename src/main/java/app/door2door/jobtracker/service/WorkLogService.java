package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.dto.WorkLogRequest;
import app.door2door.jobtracker.entity.*;
import app.door2door.jobtracker.exceptions.EntityNotFoundException;
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
                .hourDifference((request.getEndTime() - request.getStartTime()) / 3600000)
                .workCompleted(request.getWorkCompleted())
                .completed(request.isCompleted())
                .incompleteItems(request.getIncompleteItems())
                .keyNumber(request.getKeyNumber())
                .submittedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        Job job = jobRepository.findById(jobId).orElseThrow();
        job.getWorkLogs().add(workLog);
        return jobRepository.save(job);
    }

    public Job update(Integer jobId, Integer workLogId, WorkLogRequest request) {
        WorkLog workLog = workLogRepository.findById(workLogId)
                .orElseThrow(() -> new EntityNotFoundException("Log not found"));
            workLog.setCategory(request.getCategory());
            workLog.setWorkDate(request.getWorkDate());
            workLog.setStartTime(new Time(request.getStartTime()));
            workLog.setEndTime(new Time(request.getEndTime()));
            workLog.setHourDifference((request.getEndTime() - request.getStartTime()) / 3600000);
            workLog.setWorkCompleted(request.getWorkCompleted());
            workLog.setCompleted(request.isCompleted());
            workLog.setIncompleteItems(request.getIncompleteItems());
            workLog.setKeyNumber(request.getKeyNumber());
        workLogRepository.save(workLog);
        return jobRepository.findById(jobId).orElseThrow();
    }

    public Job delete(Integer jobId, Integer workLogId) {
        workLogRepository.deleteById(workLogId);
        return jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
    }
}
