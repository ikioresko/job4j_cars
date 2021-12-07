package ru.job4j.cars.servlets;

import ru.job4j.cars.model.Advert;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.AdvService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет меняет статус объявления, отмечает его как "Продан"
 *
 * @author ikioresko
 * @version 0.1
 */
public class RemoveFromSaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int advId = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        AdvService service = new AdvService();
        Advert adv = service.findAdvertByID(advId);
        if (adv.getAuthor().getId() == user.getId()) {
            adv.setSold(true);
            service.save(adv);
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
