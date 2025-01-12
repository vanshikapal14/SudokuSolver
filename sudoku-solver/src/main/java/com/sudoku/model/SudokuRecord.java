package com.sudoku.model;

import java.sql.Timestamp;

public class SudokuRecord {
    private int id;
    private String puzzle;
    private String solution;
    private Timestamp createdAt;

    // Constructors
    public SudokuRecord() {}

    public SudokuRecord(String puzzle, String solution) {
        this.puzzle = puzzle;
        this.solution = solution;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPuzzle() { return puzzle; }
    public void setPuzzle(String puzzle) { this.puzzle = puzzle; }

    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}