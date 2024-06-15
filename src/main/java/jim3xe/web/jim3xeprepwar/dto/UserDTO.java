package jim3xe.web.jim3xeprepwar.dto;

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
    private List<Integer> postIds; // Chỉ lưu id của các Post

    // Constructor, getters và setters
}
