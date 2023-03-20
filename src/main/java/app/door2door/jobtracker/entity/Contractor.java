package app.door2door.jobtracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTRACTORS")
public class Contractor {

    @Id
    @GeneratedValue
    private Integer id;

    private String companyName;

    private String contactName;

    private String phoneNumber;

    private String email;

    private Timestamp createdAt;

}
