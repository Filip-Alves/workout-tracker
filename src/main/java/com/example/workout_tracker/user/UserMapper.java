package com.example.workout_tracker.user;

import com.example.workout_tracker.user.dto.CreateUserRequest;
import com.example.workout_tracker.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    public static User toUser(CreateUserRequest request, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());

        user.setPasswordHash(passwordEncoder.encode(request.password()));
        return user;
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}