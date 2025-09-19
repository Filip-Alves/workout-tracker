package com.example.workout_tracker.workout;


import com.example.workout_tracker.exercise.Exercise;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight_kg")
    private BigDecimal weightKg;

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
}
