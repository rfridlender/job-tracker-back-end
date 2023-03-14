package app.door2door.jobtracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JOBS")
public class Job {

    @Id
    @GeneratedValue
    private Integer id;

    private String address;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String lockStatus;

    private String shelvingStatus;

    private String showerStatus;

    private String mirrorStatus;

    @OneToMany(mappedBy = "job")
    private List<WorkLog> workLogs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Contractor contractor;

    private String createdBy;

    private Timestamp createdAt;

}
