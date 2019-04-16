package controller;

import entity.Account;
import models.AccountModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username + password);

        Account account = new Account(username, password);
        if (!account.isValid()){
            HashMap<String, ArrayList<String>> errors = account.getErrors();
            req.setAttribute("account",account);
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else {
            AccountModel accountModel = new AccountModel();
            account = accountModel.check(username, password);
            if (account!= null){
                HttpSession session = req.getSession();
                session.setAttribute("LogUsername", account.getUsername());
                session.setAttribute("LogUserId", account.getAccountId());
                
            }
        }
    }
}
