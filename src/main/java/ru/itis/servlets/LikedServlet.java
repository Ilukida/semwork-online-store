package ru.itis.servlets;

import ru.itis.dto.UserDto;
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
import java.util.List;

@WebServlet("/liked")
public class LikedServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(false).getAttribute("user");

        Integer userId = userDto.getId();

        List<Product> liked = productService.getAllProductsFormLiked(userId);
        request.setAttribute("liked", liked);

        request.getRequestDispatcher("liked.ftl").forward(request, response);
    }
}
