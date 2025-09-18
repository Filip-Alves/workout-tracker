CREATE TABLE workouts (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    notes TEXT,

    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE workout_exercises (
    id BIGSERIAL PRIMARY KEY,

    workout_id BIGINT NOT NULL REFERENCES workouts(id) ON DELETE CASCADE,

    exercise_id BIGINT NOT NULL REFERENCES exercises(id) ON DELETE CASCADE,

    sets INTEGER NOT NULL,
    reps INTEGER NOT NULL,
    weight_kg DECIMAL(6, 2),
    order_index INTEGER,
    notes TEXT,

    UNIQUE(workout_id, exercise_id)
);