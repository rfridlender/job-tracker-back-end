package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.JobRequest;
import app.door2door.jobtracker.dto.JobUpdateRequest;
import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.entity.*;
import app.door2door.jobtracker.exceptions.EntityNotFoundException;
import app.door2door.jobtracker.mapper.UserDtoMapper;
import app.door2door.jobtracker.repository.JobRepository;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserDtoMapper userDtoMapper;
    private final Cloudinary cloudinary;

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
        return jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
    }

    public Job update(Integer jobId, JobUpdateRequest request) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
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
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));;
            job.getWorkLogs().clear();
        jobRepository.delete(job);
        return job;
    }

    public String addPhoto(Integer jobId, MultipartFile photo) throws IOException {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        Map uploadResponse = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
            job.setTakeoff(uploadResponse.get("url").toString());
        jobRepository.save(job);
        return uploadResponse.get("url").toString();
    }
}
