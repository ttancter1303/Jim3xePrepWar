package jim3xe.web.jim3xeprepwar.controller;

import jakarta.validation.Valid;
import jim3xe.web.jim3xeprepwar.component.JwtTokenProvider;
import jim3xe.web.jim3xeprepwar.dto.UserDTO;
import jim3xe.web.jim3xeprepwar.model.User;
import jim3xe.web.jim3xeprepwar.payload.JwtResponse;
import jim3xe.web.jim3xeprepwar.payload.LoginRequest;
import jim3xe.web.jim3xeprepwar.payload.SignUpRequest;
import jim3xe.web.jim3xeprepwar.repository.UserRepository;
import jim3xe.web.jim3xeprepwar.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String jwt = tokenProvider.generateToken(userDetails);
            UserDTO userDTO = userDetails.toUserDTO();

            return ResponseEntity.ok(new JwtResponse(jwt, userDTO));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken!");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setRole("ROLE_USER");
        user.setCreatedAt(LocalDateTime.from(LocalDate.now()));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
