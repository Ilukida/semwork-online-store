package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.models.Support;
import ru.itis.services.SupportService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/support")
public class SupportServlet extends HttpServlet {
    private SupportService supportService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.supportService = (SupportService) context.getAttribute("supportService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("support.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String problem = request.getParameter("problem");
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");

        Support support = Support.builder()
                .problem(problem)
                .userId(userDto.getId())
                .build();

        supportService.save(support);

        request.getRequestDispatcher("thanks.ftl").forward(request,response);
    }
}
