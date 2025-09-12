package com.example.workout_tracker.user.dto;

import java.time.Instant;

public record UserResponse(Long id, String username, String email, Instant createdAt) {
}