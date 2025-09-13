package com.example.workout_tracker.user;

import com.example.workout_tracker.user.dto.CreateUserRequest;
import com.example.workout_tracker.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
