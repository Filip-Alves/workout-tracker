package com.example.workout_tracker.user;

import com.example.workout_tracker.user.dto.CreateUserRequest;
import com.example.workout_tracker.user.dto.UserResponse;

public class UserMapper {

    public static User toUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());

        // temporaire :)
        user.setPasswordHash(request.password());
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