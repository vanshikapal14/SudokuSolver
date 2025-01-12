<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sudoku Solutions History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Previous Sudoku Solutions</h1>
        
        <div class="mt-4">
            <c:forEach items="${records}" var="record">
                <div class="card mb-4">
                    <div class="card-header">
                        Solved on: ${record.createdAt}
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <h5>Original Puzzle</h5>
                                <pre>${record.puzzle}</pre>
                            </div>
                            <div class="col-md-6">
                                <h5>Solution</h5>
                                <pre>${record.solution}</pre>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <a href="." class="btn btn-primary">Solve New Puzzle</a>
    </div>
</body>
</html>
