package com.example.workout_tracker.workout;

import com.example.workout_tracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByUser(User user);
}
