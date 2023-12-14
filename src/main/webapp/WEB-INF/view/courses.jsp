<%@ page import="java.util.List" %>
<%@ page import="models.Course" %>
<%@ page import="models.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<h2>List of Students</h2>
<ul>
    <% List<Student> students = (List<Student>) request.getAttribute("students"); %>
    <% for (Student student : students) { %>
    <li>
        <%= student.getName() %>
        <ul>
            <% List<Course> studentCourses = student.getCourses(); %>
            <% for (Course course : studentCourses) { %>
            <li><%= course.getName() %></li>
            <% } %>
        </ul>
        <form action="students" method="post" style="display: inline;">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="studentId" value="<%= student.getId() %>">
            <input type="text" name="studentName" placeholder="New Name" required>
            <button type="submit">Update</button>
        </form>
        <form action="students" method="post" style="display: inline;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="studentId" value="<%= student.getId() %>">
            <button type="submit">Delete</button>
        </form>
    </li>
    <% } %>
</ul>

<h3>Add New Student</h3>
<form action="students" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="studentName" placeholder="Name" required>
    <button type="submit">Add Student</button>
</form>
</body>
</html>
