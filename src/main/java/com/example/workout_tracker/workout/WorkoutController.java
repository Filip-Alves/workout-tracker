package com.example.workout_tracker.workout;


import com.example.workout_tracker.user.User;
import com.example.workout_tracker.workout.dto.CreateWorkoutRequest;
import com.example.workout_tracker.workout.dto.WorkoutResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

    private final WorkoutRepository workoutRepository;

    public WorkoutController(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
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


}
