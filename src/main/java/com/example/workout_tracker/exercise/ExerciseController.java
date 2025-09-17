package com.example.workout_tracker.exercise;

import com.example.workout_tracker.exercise.dto.CreateExerciseRequest;
import com.example.workout_tracker.exercise.dto.ExerciseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
}
