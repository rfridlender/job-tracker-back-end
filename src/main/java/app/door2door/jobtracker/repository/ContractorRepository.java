package app.door2door.jobtracker.repository;

import app.door2door.jobtracker.entity.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Integer> {

}
