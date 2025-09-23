package com.example.workout_tracker.workout;

import com.example.workout_tracker.exercise.Exercise;
import com.example.workout_tracker.workout.dto.WorkoutExerciseResponse;

public class WorkoutExerciseMapper {

    public static WorkoutExerciseResponse toWorkoutExerciseResponse(WorkoutExercise workoutExercise) {

        Exercise exerciseEntity = workoutExercise.getExercise();

        WorkoutExerciseResponse.ExerciseSummary exerciseSummary = new WorkoutExerciseResponse.ExerciseSummary(
                exerciseEntity.getId(),
                exerciseEntity.getName(),
                exerciseEntity.getMuscleGroups()
        );

        return new WorkoutExerciseResponse(
                workoutExercise.getId(),
                workoutExercise.getSets(),
                workoutExercise.getReps(),
                workoutExercise.getWeightKg(),
                workoutExercise.getOrderIndex(),
                exerciseSummary
        );

    }
}
