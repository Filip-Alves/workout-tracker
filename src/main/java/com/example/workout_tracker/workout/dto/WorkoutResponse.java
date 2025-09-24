package com.example.workout_tracker.workout.dto;

import java.util.List;

public record WorkoutResponse(
        Long id,
        String name,
        String description,
        String notes,
        List<WorkoutExerciseResponse> exercises
) {}
