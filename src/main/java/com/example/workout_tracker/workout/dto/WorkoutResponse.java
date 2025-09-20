package com.example.workout_tracker.workout.dto;

public record WorkoutResponse(
        Long id,
        String name,
        String description,
        String notes
) {}
