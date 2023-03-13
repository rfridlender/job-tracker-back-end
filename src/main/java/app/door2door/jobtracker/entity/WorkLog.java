package app.door2door.jobtracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WORK_LOGS")
public class WorkLog {

    @Id
    @GeneratedValue
    private Integer id;

    private java.sql.Timestamp submittedAt;

    private String employeeName;

    private String jobSiteAddress;

    @Enumerated(EnumType.STRING)
    private Category category;

    private java.sql.Date workDate;

    private java.sql.Time startTime;

    private java.sql.Time endTime;

    private String workCompleted;

    private boolean isCompleted;

    private String incompleteItems;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

}
