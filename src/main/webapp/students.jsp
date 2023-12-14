<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Students</title>
</head>
<body>
<h2>Students</h2>

<!-- Форма для добавления студента -->
<form action="${pageContext.request.contextPath}/students" method="post">
    <label for="studentName">Student Name:</label>
    <input type="text" id="studentName" name="studentName" required>
    <button type="submit">Add Student</button>
</form>

<hr>

<!-- Список студентов -->
<h2>List of Students:</h2>
<ul>
    <jsp:useBean id="students" scope="request" type="java.util.List"/>
    <c:forEach var="student" items="${students}">
        <li>${student.name}
            <!-- Форма для обновления студента -->
            <form id="updateForm${student.id}" onsubmit="updateStudent(${student.id}); return false;">
                <input type="text" id="updatedStudentName${student.id}" placeholder="New Name" required>
                <button type="submit">Update</button>
            </form>

            <!-- Форма для удаления студента -->
            <form id="deleteForm${student.id}" onsubmit="deleteStudent(${student.id}); return false;">
                <button type="submit">Delete</button>
            </form>
        </li>
    </c:forEach>
</ul>

<script>
    function updateStudent(studentId) {
        var updatedStudentName = document.getElementById('updatedStudentName' + studentId).value;

        fetch('${pageContext.request.contextPath}/students?_method=put&studentId=' + studentId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'updatedStudentName=' + updatedStudentName,
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                location.reload();
            })
            .catch(error => console.error('Error:', error));
    }

    function deleteStudent(studentId) {
        fetch('${pageContext.request.contextPath}/students?_method=delete&studentId=' + studentId, {
            method: 'POST',
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                location.reload();
            })
            .catch(error => console.error('Error:', error));
    }
</script>
</body>
</html>
