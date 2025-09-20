package com.example.workout_tracker.workout.dto;

public record CreateWorkoutRequest(
        String name,
        String description,
        String notes
) {
}
