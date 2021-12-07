package ru.job4j.cars.servlets;

import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет совершает регистрацию пользователя
 *
 * @author ikioresko
 * @version 0.1
 */
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("login");
        UserService service = new UserService();
        if (service.getUserByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
            service.regUser(user);
            req.setAttribute("info", "Регистрация завершена");
        } else {
            req.setAttribute("info", "Пользователь с таким логином уже существует");
        }
        req.getRequestDispatcher("/reg.jsp").forward(req, resp);
    }
}
