package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Test 1, 2: Must have @WebServlet and URL patterns
@WebServlet(urlPatterns = "/hello-servlet")
public class HelloServlet extends HttpServlet { // Test 3: Must extend HttpServlet
    
    // Test 8: Class must be public
    // Test 7: No custom state fields (stateless)

    // Test 4: doGet must be protected
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello World");
    }
}