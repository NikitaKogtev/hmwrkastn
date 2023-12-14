package servlets;

import dao.CourseDAO;
import models.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        req.setAttribute("courses", courses);

        req.getRequestDispatcher("courses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseName = req.getParameter("courseName");

        if (courseName != null && !courseName.isEmpty()) {
            Course course = new Course();
            course.setName(courseName);

            courseDAO.addCourse(course);
        }

        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Обновление курса
        String courseIdString = req.getParameter("courseId");
        String updatedCourseName = req.getParameter("updatedCourseName");

        if (courseIdString != null && updatedCourseName != null && !updatedCourseName.isEmpty()) {
            int courseId = Integer.parseInt(courseIdString);

            Course course = new Course();
            course.setId(courseId);
            course.setName(updatedCourseName);

            courseDAO.updateCourse(course);
        }

        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Удаление курса
        String courseIdString = req.getParameter("courseId");

        if (courseIdString != null) {
            int courseId = Integer.parseInt(courseIdString);
            courseDAO.deleteCourse(courseId);
        }

        resp.sendRedirect(req.getContextPath() + "/courses");
    }

}
