package servlets;

import dao.PersonDAO;
import models.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PersonServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() throws ServletException {
        personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = req.getServletPath();

        System.out.println(view);

        if ("/people".equals(view)){
            personsList(req, resp);
        } else if ("/people/1".equals(view)){
            try {
                personShow(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void personsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> personList = personDAO.index();

        req.setAttribute("people", personList);
        req.getRequestDispatcher("WEB-INF/view/people/index.jsp").forward(req, resp);
    }

    private void personShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.getRequestDispatcher("WEB-INF/view/people/show.jsp").forward(req, resp);
        int id = 1;
        Person person = personDAO.show(id);
        req.setAttribute("person", person);
    }

    private void newPerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/people/new.jsp").forward(req, resp);
    }

}
