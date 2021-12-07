package ru.job4j.cars.servlets;

import ru.job4j.cars.service.AdvService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет возвращает объявление найденное по ID для отображения его полей в adv.jsp
 *
 * @author ikioresko
 * @version 0.1
 */
public class AdvServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("advert", new AdvService().findAdvertByID(Integer.parseInt(req.getQueryString())));
        req.getRequestDispatcher("adv.jsp").forward(req, resp);
    }
}
