package com.example.workout_tracker.workout.dto;

import java.math.BigDecimal;

public record AddExerciseToWorkoutRequest(
        Long exerciseId, // L'ID de l'exercice à ajouter
        int sets,
        int reps,
        BigDecimal weightKg,
        Integer orderIndex,
        String notes
) {}