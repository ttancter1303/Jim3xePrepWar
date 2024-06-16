package jim3xe.web.jim3xeprepwar.dto;

import jim3xe.web.jim3xeprepwar.model.Post;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class UserDTO {
    private int id;
    private String username;
    private String name;
    private String avatarImg;
    private String role;
    private LocalDateTime createdAt;
    private List<Integer> postIds;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String name, String avatarImg, String role, LocalDateTime createdAt, List<Post> posts) {
    }
}
