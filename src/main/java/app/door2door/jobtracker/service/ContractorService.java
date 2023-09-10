package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.ContractorRequest;
import app.door2door.jobtracker.entity.Contractor;
import app.door2door.jobtracker.entity.Job;
import app.door2door.jobtracker.exceptions.EntityNotFoundException;
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

    public List<Contractor> index() {
        return contractorRepository.findAll();
    }

    public Contractor show(Integer contractorId) {
        return contractorRepository.findById(contractorId)
            .orElseThrow(() -> new EntityNotFoundException("Contractor not found"));
    }

    public Contractor create(ContractorRequest request) {
        Contractor contractor = Contractor.builder()
                .companyName(request.getCompanyName())
                .contactName(request.getContactName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
        return contractorRepository.save(contractor);
    }

    public Contractor update(Integer contractorId, ContractorRequest request) {
        Contractor contractor = contractorRepository.findById(contractorId)
                .orElseThrow(() -> new EntityNotFoundException("Contractor not found"));
            contractor.setCompanyName(request.getCompanyName());
            contractor.setContactName(request.getContactName());
            contractor.setPhoneNumber(request.getPhoneNumber());
            contractor.setEmail(request.getEmail());
        return contractorRepository.save(contractor);
    }

    public Contractor delete(Integer contractorId) {
        Contractor contractor = contractorRepository.findById(contractorId)
                .orElseThrow(() -> new EntityNotFoundException("Contractor not found"));
        List<Job> jobs = jobRepository.findByContractorId(contractorId);
        jobRepository.deleteAll(jobs);
        contractorRepository.delete(contractor);
        return contractor;
    }

}
