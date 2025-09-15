package com.example.workout_tracker.user;

import com.example.workout_tracker.user.dto.CreateUserRequest;
import com.example.workout_tracker.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User newUser = UserMapper.toUser(request, passwordEncoder);

        User savedUser = userRepository.save(newUser);

        return UserMapper.toUserResponse(savedUser);
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = (User) userDetails;

        return UserMapper.toUserResponse(currentUser);
    }
}
