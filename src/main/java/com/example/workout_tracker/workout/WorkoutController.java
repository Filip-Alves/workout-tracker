package com.example.workout_tracker.workout;


import com.example.workout_tracker.exception.ResourceNotFoundException;
import com.example.workout_tracker.exercise.Exercise;
import com.example.workout_tracker.exercise.ExerciseRepository;
import com.example.workout_tracker.user.User;
import com.example.workout_tracker.workout.dto.AddExerciseToWorkoutRequest;
import com.example.workout_tracker.workout.dto.CreateWorkoutRequest;
import com.example.workout_tracker.workout.dto.WorkoutExerciseResponse;
import com.example.workout_tracker.workout.dto.WorkoutResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutController(
            WorkoutRepository workoutRepository,
            WorkoutExerciseRepository workoutExerciseRepository,
            ExerciseRepository exerciseRepository
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @PostMapping
    public WorkoutResponse createWorkout(@RequestBody CreateWorkoutRequest request, @AuthenticationPrincipal User currentUser) {

        Workout workoutToSave = WorkoutMapper.toWorkout(request);
        workoutToSave.setUser(currentUser);

        Workout savedWorkout = workoutRepository.save(workoutToSave);

        return WorkoutMapper.toWorkoutResponse(savedWorkout);
    }

    @GetMapping
    public List<WorkoutResponse> getWorkoutsForCurrentUser(@AuthenticationPrincipal User currentUser) {

        List<Workout> workouts = workoutRepository.findByUser(currentUser);

        return workouts.stream()
                .map(WorkoutMapper::toWorkoutResponse)
                .toList();
    }

    @PostMapping("/{workoutId}/exercises")
    public WorkoutExerciseResponse addExerciseToWorkout(
            @PathVariable Long workoutId,
            @RequestBody AddExerciseToWorkoutRequest request,
            @AuthenticationPrincipal User currentUser) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));

        if (!workout.getUser().getId().equals(currentUser.getId())) {
            throw new ResourceNotFoundException("Workout not found with id: " + workoutId);
        }

        Exercise exercise = exerciseRepository.findById(request.exerciseId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + request.exerciseId()));

        WorkoutExercise newWorkoutExercise = new WorkoutExercise();
        newWorkoutExercise.setWorkout(workout);
        newWorkoutExercise.setExercise(exercise);

        newWorkoutExercise.setSets(request.sets());
        newWorkoutExercise.setReps(request.reps());
        newWorkoutExercise.setWeightKg(request.weightKg());
        newWorkoutExercise.setOrderIndex(request.orderIndex());
        newWorkoutExercise.setNotes(request.notes());

        WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(newWorkoutExercise);

        return WorkoutExerciseMapper.toWorkoutExerciseResponse(savedWorkoutExercise);
    }


}
