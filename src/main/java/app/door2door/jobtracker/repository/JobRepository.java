package app.door2door.jobtracker.repository;

import app.door2door.jobtracker.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
