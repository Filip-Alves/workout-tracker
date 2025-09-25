package com.example.workout_tracker.workout;

import com.example.workout_tracker.exception.ResourceNotFoundException;
import com.example.workout_tracker.exercise.Exercise;
import com.example.workout_tracker.exercise.ExerciseRepository;
import com.example.workout_tracker.user.User;
import com.example.workout_tracker.workout.dto.AddExerciseToWorkoutRequest;
import com.example.workout_tracker.workout.dto.CreateWorkoutRequest;
import com.example.workout_tracker.workout.dto.WorkoutExerciseResponse;
import com.example.workout_tracker.workout.dto.WorkoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<WorkoutResponse> getAllWorkoutsForUser(User currentUser) {
        return workoutRepository.findByUser(currentUser)
                .stream()
                .map(WorkoutMapper::toWorkoutResponse)
                .toList();
    }

    @Override
    public WorkoutResponse createWorkout(CreateWorkoutRequest request, User currentUser) {
        Workout workoutToSave = WorkoutMapper.toWorkout(request);
        workoutToSave.setUser(currentUser);
        Workout savedWorkout = workoutRepository.save(workoutToSave);
        return WorkoutMapper.toWorkoutResponse(savedWorkout);
    }

    @Override
    public WorkoutExerciseResponse addExerciseToWorkout(Long workoutId, AddExerciseToWorkoutRequest request, User currentUser) {

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

    @Override
    public WorkoutResponse getWorkoutById(Long workoutId, User currentUser) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));

        if (!workout.getUser().getId().equals(currentUser.getId())) {
            throw new ResourceNotFoundException("Workout not found with id: " + workoutId);
        }

        return WorkoutMapper.toWorkoutResponse(workout);
    }

    @Override
    public WorkoutResponse updateWorkout(Long id, CreateWorkoutRequest request, User currentUser) {
        Workout existingWorkout = workoutFounded(id, currentUser);

        existingWorkout.setName(request.name());
        existingWorkout.setDescription(request.description());
        existingWorkout.setNotes(request.notes());

        Workout savedWorkout = workoutRepository.save(existingWorkout);

        return WorkoutMapper.toWorkoutResponse(savedWorkout);
    }

    @Override
    public void deleteWorkout(Long id, User currentUser) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + id));

        if (!workout.getUser().getId().equals(currentUser.getId())) {
            throw new ResourceNotFoundException("Workout not found with id: " + id);
        }
        workoutRepository.delete(workout);
    }

    private Workout workoutFounded(Long id, User user) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + id));

        if (!workout.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Workout not found with id: " + id);
        }

        return workout;
    }
}