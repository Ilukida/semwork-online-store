package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.excptions.ValidationException;
import ru.itis.services.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-basket")
public class AddBasketServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");

        try {
            productService.saveProductInBasket(userDto.getId(), productId);
        }catch (ValidationException e) {
            return;
        }
    }
}
