package com.example.workout_tracker.workout;

import com.example.workout_tracker.workout.dto.CreateWorkoutRequest;
import com.example.workout_tracker.workout.dto.WorkoutResponse;

public class WorkoutMapper {

    public static Workout toWorkout(CreateWorkoutRequest request) {

        Workout workout = new Workout();

        workout.setName(request.name());
        workout.setDescription(request.description());
        workout.setNotes(request.notes());

        return workout;
    }

    public static WorkoutResponse toWorkoutResponse(Workout workout) {

        return new WorkoutResponse(
                workout.getId(),
                workout.getName(),
                workout.getDescription(),
                workout.getNotes(),
                workout.getWorkoutExercises().stream()
                        .map(WorkoutExerciseMapper::toWorkoutExerciseResponse).toList()
        );
    }
}
