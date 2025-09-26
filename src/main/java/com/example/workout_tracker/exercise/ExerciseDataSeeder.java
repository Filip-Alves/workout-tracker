package com.example.workout_tracker.exercise;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ExerciseDataSeeder implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Exercise> exercises = new ArrayList<>();

        if (exerciseRepository.count() == 0) {

            System.out.println("Seeding exercises data...");

            // 2. La liste est une variable locale, elle n'existe que dans cette méthode.
            Exercise pushUps = createExercise(
                    "Pompes",
                    "Un exercice de poids corporel de base pour le haut du corps.",
                    List.of("Pectoraux", "Triceps", "Épaules"),
                    ExerciseCategory.FORCE
            );

            Exercise squats = createExercise(
                    "Squat",
                    "Un exercice fondamental pour la force des jambes et du tronc.",
                    List.of("Quadriceps", "Ischio-jambiers", "Fessiers", "Tronc"),
                    ExerciseCategory.FORCE
            );

            Exercise pullUps = createExercise(
                    "Traction",
                    "Un exercice difficile pour le dos et les biceps, nécessitant de soulever son propre poids.",
                    List.of("Dos", "Biceps"),
                    ExerciseCategory.FORCE
            );

            Exercise plank = createExercise(
                    "Planche",
                    "Un exercice isométrique pour renforcer la ceinture abdominale.",
                    List.of("Tronc", "Abdominaux"),
                    ExerciseCategory.BALANCE
            );

            Exercise running = createExercise(
                    "Course à pied",
                    "Exercice cardiovasculaire classique pour l'endurance.",
                    List.of("Jambes", "Cardio"),
                    ExerciseCategory.CARDIO
            );

            Exercise yogaStretch = createExercise(
                    "Étirement Chien tête en bas",
                    "Une posture de yoga pour améliorer la flexibilité de toute la chaîne postérieure.",
                    List.of("Ischio-jambiers", "Mollets", "Dos"),
                    ExerciseCategory.FLEXIBILITY
            );

            exerciseRepository.saveAll(List.of(pushUps, squats, pullUps, plank, running, yogaStretch));

            System.out.println("Exercises data seeded.");
        } else {
            System.out.println("Exercises data already exists. No seeding needed.");
        }
    }

    private Exercise createExercise(String name, String description, List<String> muscleGroups, ExerciseCategory exerciseCategory){

        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setMuscleGroups(muscleGroups);
        exercise.setCategory(exerciseCategory);

        return exercise;
    }
}
