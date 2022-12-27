package ru.itis.servlets;

import ru.itis.models.Product;
import ru.itis.services.CategoryService;
import ru.itis.services.ProductService;
import ru.itis.services.validation.ErrorEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet("/saveProduct")
public class SaveProductServlet extends HttpServlet {

    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
        this.categoryService = (CategoryService) context.getAttribute("categoryService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("saveProduct.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Product product;

        try {
            product = Product.builder()
                    .name(request.getParameter("name"))
                    .maker(request.getParameter("maker"))
                    .price(Integer.parseInt(request.getParameter("price")))
                    .description(request.getParameter("description"))
                    .categoryId(categoryId)
                    .build();
            product = productService.save(product);

        } catch (NumberFormatException e) {
            Set<ErrorEntity> errors = new HashSet<>();
            errors.add(ErrorEntity.INVALID_REQUEST);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("saveProduct.ftl").forward(request, response);
            return;
        }

        Map<Integer, String> categoryMap = categoryService.getCategoryMap();

        HttpSession session = request.getSession(true);
        session.setAttribute("saveProduct", product);
        session.setAttribute("category", categoryMap.get(categoryId));

        response.sendRedirect("/reserved");
    }
}
