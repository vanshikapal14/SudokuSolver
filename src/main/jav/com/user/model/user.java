package com.user.model;

public class user {
	private int id;
    private String puzzle; // Store the puzzle as a string
    private String solution; // Store the solution as a string
    private String createdAt; // Store the creation date

    public user(int id, String puzzle, String solution, String createdAt) {
        this.id = id;
        this.puzzle = puzzle;
        this.solution = solution;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
    	this.createdAt = createdAt;
    }

	@Override
	public String toString() {
		return "user [id=" + id + ", puzzle=" + puzzle + ", solution=" + solution + ", createdAt=" + createdAt + "]";
	}
    
    
}

