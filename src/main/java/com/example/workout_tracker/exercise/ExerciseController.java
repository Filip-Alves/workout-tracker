package com.example.workout_tracker.exercise;

import com.example.workout_tracker.exercise.dto.CreateExerciseRequest;
import com.example.workout_tracker.exercise.dto.ExerciseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
