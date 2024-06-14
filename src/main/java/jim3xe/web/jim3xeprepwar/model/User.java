package jim3xe.web.jim3xeprepwar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String avatarImg;

    @Column()
    private String role;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Post> posts;

}
