package app.door2door.jobtracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
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

    private String employeeName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Date workDate;

    private Time startTime;

    private Time endTime;

    private BigDecimal hourDifference;

    private String workCompleted;

    private boolean completed;

    private String incompleteItems;

    private String keyNumber;

    private String workAddress;

}
