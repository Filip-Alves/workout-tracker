package com.example.workout_tracker.user;

import com.example.workout_tracker.user.dto.CreateUserRequest;
import com.example.workout_tracker.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request)throws Exception {
        User newUser = UserMapper.toUser(request);

        User savedUser = userRepository.save(newUser);

        return UserMapper.toUserResponse(savedUser);
    }
}
