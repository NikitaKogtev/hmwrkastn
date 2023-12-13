package servlets;

import dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        BookDAO bookDAO = new BookDAO();
    }

}
