package com.example.workout_tracker.workout;

import com.example.workout_tracker.user.User;
import com.example.workout_tracker.workout.dto.AddExerciseToWorkoutRequest;
import com.example.workout_tracker.workout.dto.CreateWorkoutRequest;
import com.example.workout_tracker.workout.dto.WorkoutExerciseResponse;
import com.example.workout_tracker.workout.dto.WorkoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    public WorkoutResponse createWorkout(@RequestBody CreateWorkoutRequest request, @AuthenticationPrincipal User currentUser) {

        return workoutService.createWorkout(request, currentUser);
    }

    @GetMapping
    public List<WorkoutResponse> getWorkoutsForCurrentUser(@AuthenticationPrincipal User currentUser) {

        return workoutService.getAllWorkoutsForUser(currentUser);
    }

    @PostMapping("/{workoutId}/exercises")
    public WorkoutExerciseResponse addExerciseToWorkout(
            @PathVariable Long workoutId,
            @RequestBody AddExerciseToWorkoutRequest request,
            @AuthenticationPrincipal User currentUser) {

        return workoutService.addExerciseToWorkout(workoutId, request, currentUser);
    }

    @GetMapping("/{workoutId}")
    public WorkoutResponse getWorkoutById(@PathVariable Long workoutId, @AuthenticationPrincipal User currentUser) {
        return workoutService.getWorkoutById(workoutId, currentUser);
    }

    @PutMapping("/{id}")
    public WorkoutResponse updateWorkout(
            @PathVariable Long id,
            @RequestBody CreateWorkoutRequest request,
            @AuthenticationPrincipal User currentUser) {

        return workoutService.updateWorkout(id, request, currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        workoutService.deleteWorkout(id, currentUser);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{workoutId}/exercises/{workoutExerciseId}")
    public ResponseEntity<Void> removeExerciseFromWorkout(
            @PathVariable Long workoutId,
            @PathVariable Long workoutExerciseId,
            @AuthenticationPrincipal User currentUser
    ){
        workoutService.removeExerciseFromWorkout(workoutId, workoutExerciseId, currentUser);

        return ResponseEntity.noContent().build();
    }

}
