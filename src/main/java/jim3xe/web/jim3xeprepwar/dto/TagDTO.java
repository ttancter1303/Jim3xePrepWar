package jim3xe.web.jim3xeprepwar.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TagDTO {
    private int id;
    private String name;
    private List<PostIdDTO> posts;

    public TagDTO(int id, String name, LocalDateTime createdAt, List<PostIdDTO> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostIdDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostIdDTO> posts) {
        this.posts = posts;
    }
}
