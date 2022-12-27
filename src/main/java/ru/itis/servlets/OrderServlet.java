package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.models.Order;
import ru.itis.models.Product;
import ru.itis.services.OrderService;

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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.orderService = (OrderService) context.getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        request.setAttribute("basket", session.getAttribute("basket"));
        request.setAttribute("amount", session.getAttribute("amount"));
        request.getRequestDispatcher("order.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        UserDto userDto = (UserDto) session.getAttribute("user");

        Order order = Order.builder()
                .city(request.getParameter("city"))
                .street(request.getParameter("street"))
                .house(request.getParameter("house"))
                .apartment(request.getParameter("apartment"))
                .cardOwner(request.getParameter("cardOwner"))
                .cartNumber(request.getParameter("cartNumber"))
                .expiration(request.getParameter("expiration"))
                .cvv(request.getParameter("cvv"))
                .amount((Integer) session.getAttribute("amount"))
                .productList((List<Product>) session.getAttribute("basket"))
                .userId(userDto.getId())
                .build();

        orderService.save(order);

        response.sendRedirect("done-order");
    }
}
