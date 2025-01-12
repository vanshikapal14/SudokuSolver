<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sudoku Solver</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Sudoku Solver</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="solve" method="post" class="mt-4">
            <div class="form-group">
                <label for="puzzle">Enter your Sudoku puzzle (use 0 for empty cells):</label>
                <textarea name="puzzle" id="puzzle" class="form-control" rows="9" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Solve Puzzle</button>
        </form>

        <a href="records" class="btn btn-secondary mt-3">View Previous Solutions</a>
    </div>
</body>
</html>
