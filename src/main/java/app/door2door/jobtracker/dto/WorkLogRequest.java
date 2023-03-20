package app.door2door.jobtracker.dto;

import app.door2door.jobtracker.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkLogRequest {

    private Category category;
    private String workDate;
    private String startTime;
    private String endTime;
    private String workCompleted;
    private boolean completed;
    private String incompleteItems;
    private String keyNumber;

}
