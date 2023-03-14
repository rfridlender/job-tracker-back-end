package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.ContractorRequest;
import app.door2door.jobtracker.entity.Contractor;
import app.door2door.jobtracker.repository.ContractorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractorService {

    private final ContractorRepository contractorRepository;

    public Contractor create(ContractorRequest request) {
        Contractor contractor = Contractor.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
        contractorRepository.save(contractor);
        return contractor;
    }

    public List<Contractor> index() {
        return contractorRepository.findAll();
    }

    public Contractor show(Integer contractorId) { return contractorRepository.findById(contractorId).orElseThrow(); }

    public Contractor delete(Integer contractorId) {
        Contractor contractor = contractorRepository.findById(contractorId).orElseThrow();
        contractorRepository.delete(contractor);
        return contractor;
    }
}
