package app.door2door.jobtracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BUILDERS")
public class Contractor {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "builder")
    private List<Job> jobs = new ArrayList<>();

}
