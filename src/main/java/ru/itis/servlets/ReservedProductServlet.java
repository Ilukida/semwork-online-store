package ru.itis.servlets;

import ru.itis.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/reserved")
public class ReservedProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Product product = (Product) session.getAttribute("saveProduct");
        String categoryName = (String) session.getAttribute("category");

        request.setAttribute("saveProduct", product);
        request.setAttribute("category", categoryName);

        request.getRequestDispatcher("reservedProduct.ftl").forward(request, response);
    }

}
