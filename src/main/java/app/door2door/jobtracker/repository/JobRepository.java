package app.door2door.jobtracker.repository;

import app.door2door.jobtracker.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByContractorId(Integer contractorId);

}
