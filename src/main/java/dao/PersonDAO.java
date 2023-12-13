package dao;

import models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/library";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qq123tt";

    private static Connection connection;

    public PersonDAO() {
    }

    protected Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person");) {

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            Person person = new Person();

            while (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                people.add(person);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return people;
    }

    public Person show(int id) throws SQLException {
        System.out.println("SHOW PERSON");
        Person person = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?")) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                person = new Person(name, age);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return person;
    }

//    public void save (Person person) {
//        Person person = new Person();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "INSERT INTO Person(name, year) VALUES(?, ?)";
//            ResultSet resultSet = statement.executeQuery(SQL);
//            while (resultSet.next()) {
//                person.setId(id);
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return person;
//
//
//    }

}
