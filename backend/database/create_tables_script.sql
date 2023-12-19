-- Instructions:
-- You need to run one piece each time. Running the entire file will result in error.
--
-- Create the ingredient table
--
CREATE SEQUENCE ingredient_seq;
--
CREATE TABLE IF NOT exists ingredient (
    id INTEGER DEFAULT nextval('ingredient_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    package_quantity DECIMAL(10, 2) NOT NULL,
    quantity_unit VARCHAR(50) NOT NULL,
    photo BYTEA, -- BLOB data type for storing binary data
    bar_code VARCHAR(255)
);
-- Create the recipe table
--
CREATE SEQUENCE recipe_seq;
--
CREATE TABLE IF NOT exists recipe (
    id INTEGER DEFAULT nextval('recipe_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    photo BYTEA, -- BLOB data type for storing binary data
    steps TEXT NOT NULL,
    portion INTEGER NOT NULL
);
-- Create the junction table for the many-to-many relationship
CREATE TABLE IF NOT exists recipe_ingredient (
    recipe_id INTEGER REFERENCES recipe(id),
    ingredient_id INTEGER REFERENCES ingredient(id),
    ingredient_quantity INTEGER not null,
    PRIMARY KEY (recipe_id, ingredient_id)
);
-- Create MyFridge Table
CREATE TABLE IF NOT exists my_fridge (
    user_id INTEGER not NULL,
    ingredient_id INTEGER REFERENCES ingredient(id),
    available_quantity DECIMAL(10, 2) not null,
    quantity_unit VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
);
-- Create ShoppingList Table
CREATE TABLE IF NOT exists shopping_list (
    user_id INTEGER not NULL,
    ingredient_id INTEGER REFERENCES ingredient(id),
    quantity DECIMAL(10, 2) not null,
    quantity_unit VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
);