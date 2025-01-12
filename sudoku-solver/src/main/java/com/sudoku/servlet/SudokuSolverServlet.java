package com.sudoku.servlet;

import com.sudoku.dao.SudokuDAO;
import com.sudoku.model.SudokuRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solve")
public class SudokuSolverServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SudokuDAO sudokuDAO = new SudokuDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String puzzle = request.getParameter("puzzle");
        
        try {
            // Implement your Sudoku solving algorithm here
            String solution = solveSudoku(puzzle);
            
            SudokuRecord record = new SudokuRecord(puzzle, solution);
            sudokuDAO.save(record);
            
            request.setAttribute("puzzle", puzzle);
            request.setAttribute("solution", solution);
            request.getRequestDispatcher("/WEB-INF/jsp/solution.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to solve the puzzle: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }

    private String solveSudoku(String puzzle) {
        // Placeholder for Sudoku solving logic
        return puzzle; // Replace with solved puzzle
    }
}