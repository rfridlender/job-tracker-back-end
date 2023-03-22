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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import static java.lang.Integer.parseInt;

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
        Date workDate = new Date(
                parseInt(request.getWorkDate().substring(0, 4)) - 1900,
                parseInt(request.getWorkDate().substring(5, 7)) - 1,
                parseInt(request.getWorkDate().substring(8))
        );
        Time startTime = new Time(
                parseInt(request.getStartTime().substring(0, 2)),
                parseInt(request.getStartTime().substring(3, 5)),
                00
        );
        Time endTime = new Time(
                parseInt(request.getEndTime().substring(0, 2)),
                parseInt(request.getEndTime().substring(3, 5)),
                00
        );
        Double millisecondDifference = Double.longBitsToDouble(endTime.getTime() - startTime.getTime());
        Double millisecondHour = Double.longBitsToDouble(3600000);
        BigDecimal hourDifferenceDecimal =
                new BigDecimal(millisecondDifference / millisecondHour)
                    .setScale(2, RoundingMode.HALF_UP);
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        WorkLog workLog = WorkLog.builder()
                .employeeName(getUser().name())
                .category(request.getCategory())
                .workDate(workDate)
                .startTime(startTime)
                .endTime(endTime)
                .hourDifference(hourDifferenceDecimal)
                .workCompleted(request.getWorkCompleted())
                .completed(request.isCompleted())
                .incompleteItems(request.getIncompleteItems())
                .keyNumber(request.getKeyNumber())
                .workAddress(job.getAddress())
                .build();
        job.getWorkLogs().add(workLog);
        return jobRepository.save(job);
    }

    public Job update(Integer jobId, Integer workLogId, WorkLogRequest request) {
        Date workDate = new Date(
                parseInt(request.getWorkDate().substring(0, 4)) - 1900,
                parseInt(request.getWorkDate().substring(5, 7)) - 1,
                parseInt(request.getWorkDate().substring(8))
        );
        Time startTime = new Time(
                parseInt(request.getStartTime().substring(0, 2)),
                parseInt(request.getStartTime().substring(3, 5)),
                00
        );
        Time endTime = new Time(
                parseInt(request.getEndTime().substring(0, 2)),
                parseInt(request.getEndTime().substring(3, 5)),
                00
        );
        Double millisecondDifference = Double.longBitsToDouble(endTime.getTime() - startTime.getTime());
        Double millisecondHour = Double.longBitsToDouble(3600000);
        BigDecimal hourDifferenceDecimal =
                new BigDecimal(millisecondDifference / millisecondHour)
                        .setScale(2, RoundingMode.HALF_UP);
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        WorkLog workLog = workLogRepository.findById(workLogId)
                .orElseThrow(() -> new EntityNotFoundException("Log not found"));
            workLog.setCategory(request.getCategory());
            workLog.setWorkDate(workDate);
            workLog.setStartTime(startTime);
            workLog.setEndTime(endTime);
            workLog.setHourDifference(hourDifferenceDecimal);
            workLog.setWorkCompleted(request.getWorkCompleted());
            workLog.setCompleted(request.isCompleted());
            workLog.setIncompleteItems(request.getIncompleteItems());
            workLog.setKeyNumber(request.getKeyNumber());
            workLog.setWorkAddress(job.getAddress());
        workLogRepository.save(workLog);
        return job;
    }

    public Job delete(Integer jobId, Integer workLogId) {
        workLogRepository.deleteById(workLogId);
        return jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
    }
}
