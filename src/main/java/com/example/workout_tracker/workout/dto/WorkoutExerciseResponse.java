package com.example.workout_tracker.workout.dto;


import java.math.BigDecimal;
import java.util.List;

public record WorkoutExerciseResponse(
        Long id,
        int sets,
        int reps,
        BigDecimal weightKg,
        Integer orderIndex,
        ExerciseSummary exercise
) {
    public record ExerciseSummary(
            Long id,
            String name,
            List<String> muscleGroups
    ) {}
}
