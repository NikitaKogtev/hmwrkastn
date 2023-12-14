package servlets;

import dao.CourseDAO;
import dao.StudentDAO;
import models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        req.setAttribute("students", students);

        req.getRequestDispatcher("students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("studentName");

        if (studentName != null && !studentName.isEmpty()) {
            Student student = new Student();
            student.setName(studentName);

            studentDAO.addStudent(student);
        }

        resp.sendRedirect(req.getContextPath() + "/students");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Обновление студента
        String studentIdString = req.getParameter("studentId");
        String updatedStudentName = req.getParameter("updatedStudentName");

        if (studentIdString != null && updatedStudentName != null && !updatedStudentName.isEmpty()) {
            int studentId = Integer.parseInt(studentIdString);

            Student student = new Student();
            student.setId(studentId);
            student.setName(updatedStudentName);

            studentDAO.updateStudent(student);
        }

        resp.sendRedirect(req.getContextPath() + "/students");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Удаление студента
        String studentIdString = req.getParameter("studentId");

        if (studentIdString != null) {
            int studentId = Integer.parseInt(studentIdString);
            studentDAO.deleteStudent(studentId);
        }

        resp.sendRedirect(req.getContextPath() + "/students");
    }
}