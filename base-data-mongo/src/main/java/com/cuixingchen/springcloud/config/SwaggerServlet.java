package com.cuixingchen.springcloud.config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cuipengfei on 17-7-5.
 */
@WebServlet(
        value = {"/swagger"},
        name = "swagger"
)
public class SwaggerServlet extends HttpServlet {
    public SwaggerServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/swagger/index.html");
    }
}
