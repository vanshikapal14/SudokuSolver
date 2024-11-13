package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/sudoku_solver";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";

	public class SudokuRecord {
		private int id; // Unique identifier for the record
		private String puzzle; // The Sudoku puzzle
		private String solution; // The solution to the puzzle
		private String createdAt; // Timestamp of when the record was created

		// Constructor
		public SudokuRecord(int id, String puzzle, String solution, String createdAt) {
			this.id = id;
			this.puzzle = puzzle;
			this.solution = solution;
			this.createdAt = createdAt;
		}

		// Getters
		public int getId() {
			return id;
		}

		public String getPuzzle() {
			return puzzle;
		}

		public String getSolution() {
			return solution;
		}

		public String getCreatedAt() {
			return createdAt;
		}
	}
}