package ru.job4j.cars.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс реализует фильтр и ограничивает неавторизованных пользователей
 *
 * @author ikioresko
 * @version 0.1
 */
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest sReq, ServletResponse sResp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sReq;
        HttpServletResponse resp = (HttpServletResponse) sResp;
        String uri = req.getRequestURI();
        if (uri.endsWith("login.do") || uri.endsWith("reg.do")) {
            chain.doFilter(sReq, sResp);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(sReq, sResp);
    }
}
