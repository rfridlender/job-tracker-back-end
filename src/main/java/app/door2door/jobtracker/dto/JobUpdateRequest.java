package app.door2door.jobtracker.dto;

import app.door2door.jobtracker.entity.Contractor;
import app.door2door.jobtracker.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobUpdateRequest {

    private String address;
    private Status status;
    private String lockStatus;
    private String shelvingStatus;
    private String showerStatus;
    private String mirrorStatus;
    private Contractor contractor;


}
