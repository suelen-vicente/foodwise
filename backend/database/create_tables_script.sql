-- Instructions:
-- You need to run one piece each time. Running the entire file will result in error.
--
-- Create the ingredient table
CREATE TABLE IF NOT exists ingredient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    photo BYTEA -- BLOB data type for storing binary data
);
-- Create the recipe table
CREATE TABLE IF NOT exists recipe (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    photo BYTEA, -- BLOB data type for storing binary data
    steps TEXT NOT NULL
);
-- Create the junction table for the many-to-many relationship
CREATE TABLE IF NOT exists recipe_ingredient (
    recipe_id INTEGER REFERENCES recipe(id),
    ingredient_id INTEGER REFERENCES ingredient(id),
    ingredient_quantity INTEGER not null,
    PRIMARY KEY (recipe_id, ingredient_id)
);