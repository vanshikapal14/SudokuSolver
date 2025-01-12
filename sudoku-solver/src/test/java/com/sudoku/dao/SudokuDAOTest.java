package com.sudoku.dao;

import com.sudoku.model.SudokuRecord;
import com.sudoku.util.DatabaseConnection;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

public class SudokuDAOTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet mockResultSet;
    
    private SudokuDAO sudokuDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        sudokuDAO = new SudokuDAO();
        
        // Mock DatabaseConnection.getConnection()
        mockStatic(DatabaseConnection.class);
        when(DatabaseConnection.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testSave() throws SQLException {
        SudokuRecord record = new SudokuRecord();
        record.setPuzzle("5 3 0 0 7 0 0 0 0");
        record.setSolution("5 3 4 6 7 8 9 1 2");

        // Mock PreparedStatement behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        
        sudokuDAO.save(record);
        
        // Verify that the SQL statement was executed
        verify(mockPreparedStatement).setString(1, record.getPuzzle());
        verify(mockPreparedStatement).setString(2, record.getSolution());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testGetAllRecords() throws SQLException {
        String sql = "SELECT * FROM sudoku_records ORDER BY created_at DESC";
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);
        
        // Mocking the result set
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("puzzle")).thenReturn("5 3 0 0 7 0 0 0 0");
        when(mockResultSet.getString("solution")).thenReturn("5 3 4 6 7 8 9 1 2");
        when(mockResultSet.getTimestamp("created_at")).thenReturn(Timestamp.valueOf("2025-01-11 10:10:10"));

        List<SudokuRecord> records = sudokuDAO.getAllRecords();
        
        // Verify that the list has one record and the record values
        Assertions.assertEquals(1, records.size());
        SudokuRecord record = records.get(0);
        Assertions.assertEquals("5 3 0 0 7 0 0 0 0", record.getPuzzle());
        Assertions.assertEquals("5 3 4 6 7 8 9 1 2", record.getSolution());
    }

    @Test
    public void testGetById() throws SQLException {
        int id = 1;
        SudokuRecord expectedRecord = new SudokuRecord();
        expectedRecord.setId(id);
        expectedRecord.setPuzzle("5 3 0 0 7 0 0 0 0");
        expectedRecord.setSolution("5 3 4 6 7 8 9 1 2");
        
        String sql = "SELECT * FROM sudoku_records WHERE id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        // Mocking the result set for the specified record
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("puzzle")).thenReturn("5 3 0 0 7 0 0 0 0");
        when(mockResultSet.getString("solution")).thenReturn("5 3 4 6 7 8 9 1 2");
        when(mockResultSet.getTimestamp("created_at")).thenReturn(Timestamp.valueOf("2025-01-11 10:10:10"));

        SudokuRecord record = sudokuDAO.getById(id);
        
        // Verify the returned record
        Assertions.assertNotNull(record);
        Assertions.assertEquals(expectedRecord.getPuzzle(), record.getPuzzle());
        Assertions.assertEquals(expectedRecord.getSolution(), record.getSolution());
    }

    @Test
    public void testSaveWithNullValues() throws SQLException {
        SudokuRecord record = new SudokuRecord();
        record.setPuzzle(null);
        record.setSolution(null);

        // Mock PreparedStatement behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        
        sudokuDAO.save(record);
        
        // Verify that the SQL statement was executed even with null values
        verify(mockPreparedStatement).setString(1, null);
        verify(mockPreparedStatement).setString(2, null);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testGetAllRecordsWhenEmpty() throws SQLException {
        String sql = "SELECT * FROM sudoku_records ORDER BY created_at DESC";
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);

        // Mocking an empty result set
        when(mockResultSet.next()).thenReturn(false);

        List<SudokuRecord> records = sudokuDAO.getAllRecords();
        
        // Verify that the list is empty
        Assertions.assertEquals(0, records.size());
    }

    @Test
    public void testGetByIdWhenRecordNotFound() throws SQLException {
        int id = 99; // assuming this ID does not exist
        String sql = "SELECT * FROM sudoku_records WHERE id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        // Mocking that the result set does not return any rows
        when(mockResultSet.next()).thenReturn(false);

        SudokuRecord record = sudokuDAO.getById(id);
        
        // Verify that no record is returned
        Assertions.assertNull(record);
    }

    @Test
    public void testGetByIdWithNegativeId() throws SQLException {
        int id = -1; // Invalid ID
        String sql = "SELECT * FROM sudoku_records WHERE id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Mocking that the result set does not return any rows
        when(mockResultSet.next()).thenReturn(false);

        SudokuRecord record = sudokuDAO.getById(id);
        
        // Verify that no record is returned
        Assertions.assertNull(record);
    }

    @Test
    public void testGetAllRecordsWithMultipleRecords() throws SQLException {
        String sql = "SELECT * FROM sudoku_records ORDER BY created_at DESC";
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);
        
        // Mocking multiple records in the result set
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("puzzle")).thenReturn("5 3 0 0 7 0 0 0 0", "6 2 0 0 8 0 0 0 0");
        when(mockResultSet.getString("solution")).thenReturn("5 3 4 6 7 8 9 1 2", "6 2 4 6 8 3 7 1 5");
        when(mockResultSet.getTimestamp("created_at")).thenReturn(Timestamp.valueOf("2025-01-11 10:10:10"), Timestamp.valueOf("2025-01-12 10:10:10"));

        List<SudokuRecord> records = sudokuDAO.getAllRecords();
        
        // Verify that the list has two records
        Assertions.assertEquals(2, records.size());
        Assertions.assertEquals("5 3 0 0 7 0 0 0 0", records.get(0).getPuzzle());
        Assertions.assertEquals("6 2 0 0 8 0 0 0 0", records.get(1).getPuzzle());
    }

    @AfterEach
    public void tearDown() {
        // Close any necessary resources if needed
    }
}
