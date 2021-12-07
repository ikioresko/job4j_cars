package ru.job4j.cars.servlets;

import ru.job4j.cars.model.Advert;
import ru.job4j.cars.service.AdvService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обновляет поля объявления (описание и стоимость)
 *
 * @author ikioresko
 * @version 0.1
 */
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Advert advert = new AdvService().findAdvertByID(id);
        req.setAttribute("id", id);
        req.setAttribute("price", advert.getPrice());
        req.setAttribute("desc", advert.getDescription());
        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");
        AdvService service = new AdvService();
        Advert advert = service.findAdvertByID(Integer.parseInt(req.getParameter("adv")));
        advert.setDescription(req.getParameter("description"));
        advert.setPrice(Integer.parseInt(req.getParameter("price")));
        service.save(advert);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
