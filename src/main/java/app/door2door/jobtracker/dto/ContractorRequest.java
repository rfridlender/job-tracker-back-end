package app.door2door.jobtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractorRequest {

    private String companyName;
    private String contactName;
    private String phoneNumber;
    private String email;

}

