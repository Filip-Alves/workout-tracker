package com.example.workout_tracker.workout;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    Optional<WorkoutExercise> findByIdAndWorkoutId(Long id, Long workoutId);
}
