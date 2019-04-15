package controller;

import entity.Role;
import models.RoleModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/role")
public class RoleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Role role = new Role();

        role.setName(req.getParameter("name"));
        role.setDescription("admin");
        role.setStatus(1);
        role.setCreatedAt(121212121);
        role.setUpdatedAt(234123412);

        session.beginTransaction();

        session.save(role);

        session.getTransaction().commit();
        resp.getWriter().println("HelloWorld!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
