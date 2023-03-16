package app.door2door.jobtracker.dto;

import app.door2door.jobtracker.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkLogRequest {

    private Category category;
    private Date workDate;
    private Long startTime;
    private Long endTime;
    private String workCompleted;
    private boolean completed;
    private String incompleteItems;
    private String keyNumber;

}
