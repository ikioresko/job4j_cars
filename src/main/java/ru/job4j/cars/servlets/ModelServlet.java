package ru.job4j.cars.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.Model;
import ru.job4j.cars.service.OptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет возвращает список объектов Model который относится только к определенной марки авто
 * для отображения его полей в create.jsp и выбора модели авто при создании объявления
 *
 * @author ikioresko
 * @version 0.1
 */
public class ModelServlet extends HttpServlet {
    private final static Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        int brandId = Integer.parseInt(req.getParameter("brandId"));
        List<Model> brands =
                brandId > 0 ? new OptionService().getModelByBrandID(brandId) : new ArrayList<>();
        String json = GSON.toJson(brands);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
