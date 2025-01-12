CREATE DATABASE sudoku_solver;

USE sudoku_solver;

CREATE TABLE sudoku_records (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each record
    puzzle TEXT NOT NULL,                 -- The Sudoku puzzle, stored as a string
    solution TEXT NOT NULL,               -- The solution to the Sudoku puzzle, stored as a string
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Timestamp of when the record was created
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);