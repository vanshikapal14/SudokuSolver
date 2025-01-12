package com.sudoku.servlet;

import com.sudoku.dao.SudokuDAO;
import com.sudoku.model.SudokuRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/records")
public class ViewRecordsServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SudokuDAO sudokuDAO = new SudokuDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<SudokuRecord> records = sudokuDAO.getAllRecords();
            request.setAttribute("records", records);
            request.getRequestDispatcher("/WEB-INF/jsp/records.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to retrieve records: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }
}