package jim3xe.web.jim3xeprepwar.payload;

import jim3xe.web.jim3xeprepwar.dto.UserDTO;

public class JwtResponse {
    private String token;
    private UserDTO user;


    public JwtResponse(String token, UserDTO userDTO) {
        this.token = token;
        this.user = user;

    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
