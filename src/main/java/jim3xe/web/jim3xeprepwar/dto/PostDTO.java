package jim3xe.web.jim3xeprepwar.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class PostDTO {
    private int id;
    private String thumbnail;
    private String title;
    private String slug;
    private String body;
    private String status;
    private LocalDateTime createdAt;
    private int userId; // Chỉ lưu id của User
    private List<Integer> tagIds; // Chỉ lưu id của các Tag

    // Constructor, getters và setters
}
