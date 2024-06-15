package jim3xe.web.jim3xeprepwar.service.impl;

import jim3xe.web.jim3xeprepwar.model.User;
import jim3xe.web.jim3xeprepwar.repository.UserRepository;
import jim3xe.web.jim3xeprepwar.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
     private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getDataByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Admin not found");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(user.getRole()));
        System.out.println(user);
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
    }
    public void changePassword(User account, String newPassword) {
        account.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(account);
    }
}
