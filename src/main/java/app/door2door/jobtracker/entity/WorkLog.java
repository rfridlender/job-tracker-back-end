package app.door2door.jobtracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.sql.Time;

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

    @Enumerated(EnumType.STRING)
    private Category category;

    private Date workDate;

    private Time startTime;

    private Time endTime;

    private Long hourDifference;

    private String workCompleted;

    private boolean completed;

    private String incompleteItems;

}
