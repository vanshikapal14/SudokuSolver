<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sudoku Solution</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Sudoku Solution</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="row mt-4">
            <div class="col-md-6">
                <h3>Original Puzzle</h3>
                <pre class="border p-3">${not empty puzzle ? puzzle : 'No puzzle provided.'}</pre>
            </div>
            <div class="col-md-6">
                <h3>Solution</h3>
                <pre class="border p-3">${not empty solution ? solution : 'No solution available.'}</pre>
            </div>
        </div>

        <div class="mt-4">
            <a href="." class="btn btn-primary">Solve Another Puzzle</a>
            <a href="records" class="btn btn-secondary">View All Solutions</a>
        </div>
    </div>
</body>
</html>