package ru.itis.servlets;

import ru.itis.excptions.NotFoundException;
import ru.itis.models.Product;
import ru.itis.services.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Product product;

        try {
            product = productService.getProductById(productId);
            request.setAttribute("product", product);
            request.getRequestDispatcher("product.ftl").forward(request, response);
        } catch (NotFoundException e) {
            response.sendRedirect("/catalog");
        }
    }
}
