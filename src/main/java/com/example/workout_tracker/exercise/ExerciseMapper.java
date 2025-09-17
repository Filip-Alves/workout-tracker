package com.example.workout_tracker.exercise;

import com.example.workout_tracker.exercise.dto.CreateExerciseRequest;
import com.example.workout_tracker.exercise.dto.ExerciseResponse;

public class ExerciseMapper {

    public static Exercise toExercise(CreateExerciseRequest request) {

        Exercise exercise = new Exercise();

        exercise.setName(request.name());
        exercise.setDescription(request.description());
        exercise.setMuscleGroups(request.muscleGroups());
        exercise.setCategory(request.category());

        return exercise;
    }

    public static ExerciseResponse toExerciseResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getMuscleGroups(),
                exercise.getCategory()
        );
    }
}
