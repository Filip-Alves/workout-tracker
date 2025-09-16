CREATE TYPE exercise_category AS ENUM (
    'FORCE',
    'CARDIO',
    'FLEXIBILITY',
    'BALANCE'
);

CREATE TABLE exercises (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    muscle_groups VARCHAR(255)[] NOT NULL,

    category exercise_category NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);