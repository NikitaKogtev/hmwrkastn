package dao;

import models.Student;
import util.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String SELECT_ALL_USERS = "SELECT * FROM students";
    private static final String INSERT_STUDENT = "INSERT INTO students (student_name) VALUE(?)";
    private static final String UPDATE_STUDENT = "UPDATE students SET student_name=? WHERE student_id=?";
    private static final String DELETE_STUDENT = "DELETE * FROM students WHERE student_id=?";
    private static final String INSERT_STUDENT_COURSE = "INSERT INTO student_course (student_id, course_id) VALUES (?, ?)";
    private static final String DELETE_STUDENT_COURSE = "DELETE * FROM student_course WHERE student_id=? AND course_id=?";


    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("student_name"));
                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;

    }

    public void addStudent(Student student) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateStudent(Student student) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteStudent(Student student) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {

            preparedStatement.setInt(1,student.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void enrollStudentInCourse (int studentId, int courseId) {
        try(Connection connection = DataBase.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_COURSE)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void unEnrollStudentInCourse (int studentId, int courseId) {
        try(Connection connection = DataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_COURSE)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
