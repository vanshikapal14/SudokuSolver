package com.sudoku.dao;

import com.sudoku.model.SudokuRecord;
import com.sudoku.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SudokuDAO {
    
    public void save(SudokuRecord record) throws SQLException {
        String sql = "INSERT INTO sudoku_records (puzzle, solution) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, record.getPuzzle());
            stmt.setString(2, record.getSolution());
            stmt.executeUpdate();
        }
    }

    public List<SudokuRecord> getAllRecords() throws SQLException {
        List<SudokuRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM sudoku_records ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                SudokuRecord record = new SudokuRecord();
                record.setId(rs.getInt("id"));
                record.setPuzzle(rs.getString("puzzle"));
                record.setSolution(rs.getString("solution"));
                record.setCreatedAt(rs.getTimestamp("created_at"));
                records.add(record);
            }
        }
        return records;
    }

    public SudokuRecord getById(int id) throws SQLException {
        String sql = "SELECT * FROM sudoku_records WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SudokuRecord record = new SudokuRecord();
                    record.setId(rs.getInt("id"));
                    record.setPuzzle(rs.getString("puzzle"));
                    record.setSolution(rs.getString("solution"));
                    record.setCreatedAt(rs.getTimestamp("created_at"));
                    return record;
                }
            }
        }
        return null;
    }
}