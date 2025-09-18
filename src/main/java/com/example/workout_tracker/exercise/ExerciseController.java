package com.example.workout_tracker.exercise;

import com.example.workout_tracker.exception.ResourceNotFoundException;
import com.example.workout_tracker.exercise.dto.CreateExerciseRequest;
import com.example.workout_tracker.exercise.dto.ExerciseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;

    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @PostMapping
    public ExerciseResponse createExercise(@RequestBody CreateExerciseRequest request) {

        Exercise exerciseToSave = ExerciseMapper.toExercise(request);
        Exercise savedExercise = exerciseRepository.save(exerciseToSave);

        return ExerciseMapper.toExerciseResponse(savedExercise);
    }

    @GetMapping
    public List<ExerciseResponse> getAllExercises() {
        List<Exercise> exercisesFromDb = exerciseRepository.findAll();

        return exercisesFromDb.stream()
                .map(ExerciseMapper::toExerciseResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ExerciseResponse getExerciseById(@PathVariable Long id) {
        return exerciseRepository.findById(id)
                .map(ExerciseMapper::toExerciseResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));
    }

    @PutMapping("/{id}")
    public ExerciseResponse updateExercise(@PathVariable Long id, @RequestBody CreateExerciseRequest request ) {

        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));

        existingExercise.setName(request.name());
        existingExercise.setDescription(request.description());
        existingExercise.setMuscleGroups(request.muscleGroups());
        existingExercise.setCategory(request.category());

        Exercise updatedExercise = exerciseRepository.save(existingExercise);

        return ExerciseMapper.toExerciseResponse(updatedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));

        exerciseRepository.delete(exercise);

        return ResponseEntity.noContent().build();
     }
}
