package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.ContractorRequest;
import app.door2door.jobtracker.entity.Contractor;
import app.door2door.jobtracker.entity.Job;
import app.door2door.jobtracker.repository.ContractorRepository;
import app.door2door.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractorService {

    private final ContractorRepository contractorRepository;
    private final JobRepository jobRepository;

    public Contractor create(ContractorRequest request) {
        Contractor contractor = Contractor.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
        return contractorRepository.save(contractor);
    }

    public List<Contractor> index() {
        return contractorRepository.findAll();
    }

    public Contractor show(Integer contractorId) { return contractorRepository.findById(contractorId).orElseThrow(); }

    public Contractor delete(Integer contractorId) {
        Contractor contractor = contractorRepository.findById(contractorId).orElseThrow();
        List<Job> jobs = jobRepository.findByContractorId(contractorId);
        jobRepository.deleteAll(jobs);
        contractorRepository.delete(contractor);
        return contractor;
    }

    public Contractor update(Integer contractorId, ContractorRequest request) {
        Contractor contractor = contractorRepository.findById(contractorId).orElseThrow();
        contractor.setName(request.getName());
        contractor.setPhoneNumber(request.getPhoneNumber());
        contractor.setEmail(request.getEmail());
        return contractorRepository.save(contractor);
    }
}
