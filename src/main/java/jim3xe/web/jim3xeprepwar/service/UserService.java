package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.UserDTO;
import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.model.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    // Inject UserRepository và các phương thức khác

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setAvatarImg(user.getAvatarImg());
        userDTO.setRole(user.getRole());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setPostIds(user.getPosts().stream()
                .map(Post::getId)
                .collect(Collectors.toList()));
        return userDTO;
    }

    public void createToken(){

    }
}
