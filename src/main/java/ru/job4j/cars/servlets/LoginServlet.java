package ru.job4j.cars.servlets;

import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет осуществляет авторизацию пользователя и записывает его в сессию
 *
 * @author ikioresko
 * @version 0.1
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("login");
        String password = req.getParameter("password");
        User users = new UserService().getUserByUsername(name);
        if (users != null && users.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", users);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error", "Не верное имя пользователя или пароль");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
