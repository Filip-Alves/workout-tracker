package com.example.workout_tracker.exercise.dto;

import com.example.workout_tracker.exercise.ExerciseCategory;

import java.util.List;

public record ExerciseResponse(
        Long id,
        String name,
        String description,
        List<String> muscleGroups,
        ExerciseCategory category
) {}
