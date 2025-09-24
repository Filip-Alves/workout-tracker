package com.example.workout_tracker.workout;

import com.example.workout_tracker.user.User;
import com.example.workout_tracker.workout.dto.AddExerciseToWorkoutRequest;
import com.example.workout_tracker.workout.dto.CreateWorkoutRequest;
import com.example.workout_tracker.workout.dto.WorkoutExerciseResponse;
import com.example.workout_tracker.workout.dto.WorkoutResponse;

import java.util.List;

public interface WorkoutService {

    List<WorkoutResponse> getAllWorkoutsForUser(User currentUser);

    WorkoutResponse createWorkout(CreateWorkoutRequest request, User currentUser);

    WorkoutExerciseResponse addExerciseToWorkout(Long workoutId, AddExerciseToWorkoutRequest request, User currentUser);

    WorkoutResponse getWorkoutById(Long workoutId, User currentUser);
}
