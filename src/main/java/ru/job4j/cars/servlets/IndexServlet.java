package ru.job4j.cars.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.AdvService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Сервлет возвращает список объявлений в зависимости от указанного фильтра
 * на index.jsp используя кастомный json сериализатор
 *
 * @author ikioresko
 * @version 0.1
 */
public class IndexServlet extends HttpServlet {
    private final static Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Advert.class, new AdvertGsonSerializer())
            .create();

    private final AdvService service = new AdvService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json; charset=utf-8");
        int filter = Integer.parseInt(req.getParameter("filter"));
        OutputStream output = resp.getOutputStream();
        List<Advert> list = filter == 1 ? service.getAllAdvert() : service.getAdvByLastDay();
        String json = GSON.toJson(list);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
