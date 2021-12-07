package ru.job4j.cars.servlets;

import ru.job4j.cars.model.Advert;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.AdvService;
import ru.job4j.cars.service.OptionService;
import ru.job4j.cars.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Сервлет осуществляет запись нового объекта Advert в базу данных
 *
 * @author ikioresko
 * @version 0.1
 */
@MultipartConfig
public class CreateAdvServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");
        String photo = req.getParameter("photo");
        User user = (User) req.getSession().getAttribute("user");
        OptionService optionService = new OptionService();
        Advert advert = new Advert();
        advert.setId(0);
        advert.setCreated(new Date(System.currentTimeMillis()));
        advert.setCategory(optionService.getCategoryByID(Integer.parseInt(
                req.getParameter("category"))));
        advert.setBrand(optionService.getBrandByID(Integer.parseInt(req.getParameter("brand"))));
        advert.setModel(optionService.getModelByID(Integer.parseInt(req.getParameter("model"))));
        advert.setBody(optionService.getBodyByID(Integer.parseInt(req.getParameter("body"))));
        advert.setDescription(req.getParameter("description"));
        advert.setPrice(Integer.parseInt(req.getParameter("price")));
        advert.setSold(false);
        advert.setAuthor(new UserService().getAuthorByID(user.getId()));
        advert.setPhotoPath(photo);
        new AdvService().save(advert);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
