package app.door2door.jobtracker.repository;

import app.door2door.jobtracker.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogRepository extends JpaRepository<WorkLog, Integer> {

}
