package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();
}
