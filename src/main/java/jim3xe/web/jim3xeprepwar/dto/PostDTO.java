package jim3xe.web.jim3xeprepwar.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {
    private int id;
    private int userId;  // Only the ID of the user
    private String thumbnail;
    private String title;
    private String slug;
    private String body;
    private String status;
    private LocalDateTime createdAt;
    private List<TagIdDTO> tags;

    public PostDTO(int id, int userId, String thumbnail, String title, String slug, String body, String status, LocalDateTime createdAt, List<TagIdDTO> tags) {
        this.id = id;
        this.userId = userId;
        this.thumbnail = thumbnail;
        this.title = title;
        this.slug = slug;
        this.body = body;
        this.status = status;
        this.createdAt = createdAt;
        this.tags = tags;
    }
}
