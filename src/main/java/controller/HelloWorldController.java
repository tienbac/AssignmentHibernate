package controller;

import entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello")
public class HelloWorldController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Account account = new Account();
        account.setUsername(req.getParameter("username"));
        //account.setEmail(req.getParameter("email"));
        account.setCreatedAt(3213123);
        account.setUpdatedAt(1231231);
        account.setPassword("_");
        //account.setFullName("Hung");
        account.setStatus(1);

        session.beginTransaction();

        session.save(account);

        session.getTransaction().commit();

        resp.getWriter().println("Hello World!");
    }
}
