<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
</head>
<body>
<h2>Courses</h2>

<!-- Форма для добавления курса -->
<form action="${pageContext.request.contextPath}/courses" method="post">
    <label for="courseName">Course Name:</label>
    <input type="text" id="courseName" name="courseName" required>
    <button type="submit">Add Course</button>
</form>

<hr>

<!-- Список курсов -->
<h2>List of Courses:</h2>
<ul>
    <jsp:useBean id="courses" scope="request" type="java.util.List"/>
    <c:forEach var="course" items="${courses}">
        <li>${course.name}
            <!-- Форма для обновления курса -->
            <form id="updateForm${course.id}" onsubmit="updateCourse(${course.id}); return false;">
                <input type="text" id="updatedCourseName${course.id}" placeholder="New Name" required>
                <button type="submit">Update</button>
            </form>

            <!-- Форма для удаления курса -->
            <form id="deleteForm${course.id}" onsubmit="deleteCourse(${course.id}); return false;">
                <button type="submit">Delete</button>
            </form>
        </li>
    </c:forEach>
</ul>

<script>
    function updateCourse(courseId) {
        var updatedCourseName = document.getElementById('updatedCourseName' + courseId).value;

        fetch('${pageContext.request.contextPath}/courses?_method=put&courseId=' + courseId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'updatedCourseName=' + updatedCourseName,
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                location.reload();
            })
            .catch(error => console.error('Error:', error));
    }

    function deleteCourse(courseId) {
        fetch('${pageContext.request.contextPath}/courses?_method=delete&courseId=' + courseId, {
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
