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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        UserDto userDto = (UserDto) session.getAttribute("user");

        Integer userId = userDto.getId();

        List<Product> basket = productService.getAllProductsInBasket(userId);
        Integer amount = productService.amount(userId);

        request.setAttribute("basket", basket);
        session.setAttribute("basket", basket);
        session.setAttribute("amount", amount);

        request.getRequestDispatcher("basket.ftl").forward(request, response);
    }
}
