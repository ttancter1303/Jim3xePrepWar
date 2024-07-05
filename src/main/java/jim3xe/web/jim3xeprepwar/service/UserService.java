package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.UserDTO;
import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.model.User;
import jim3xe.web.jim3xeprepwar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAccountByUsername(username);
        UserDTO userDTO = convertToDTO(user);
        if (userDTO == null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(userDTO);
    }
    @Transactional
    public UserDTO loadUserDTOById(int id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + id)
        );
        return convertToDTO(user);
    }
    @Transactional
    public CustomUserDetails loadUserById(int id) {
        UserDTO userDTO = loadUserDTOById(id);
        return new CustomUserDetails(userDTO);
    }
}
