package jim3xe.web.jim3xeprepwar.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class TagDTO {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private List<Integer> postIds;
}
