package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

[cite_start]@WebServlet(urlPatterns = "/hello-servlet") // [cite: 626, 831]
public class HelloServlet extends HttpServlet {
    @Override
    [cite_start]protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException { // [cite: 628, 835]
        resp.setContentType("text/plain"); [cite_start]// [cite: 629]
        resp.getWriter().write("Hello, Servlet is working!"); [cite_start]// [cite: 630]
    }
}