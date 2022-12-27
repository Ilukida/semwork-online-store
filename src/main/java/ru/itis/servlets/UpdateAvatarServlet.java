package ru.itis.servlets;
import ru.itis.dto.UserDto;
import ru.itis.models.FileInfo;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/file-upload")
@MultipartConfig
public class UpdateAvatarServlet extends HttpServlet {

    private FileService fileService;

    @Override
    public void init(ServletConfig config) {
        this.fileService = (FileService) config.getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "http://localhost:8080/file-upload";
        request.setAttribute("path", path);
        request.getRequestDispatcher("fileUpload.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");

        HttpSession session = request.getSession(true);
        UserDto userDto = (UserDto) session.getAttribute("user");

        FileInfo fileInfo = fileService.saveFileToStorageProfile(
                userDto,
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                (int) part.getSize());

        userDto.setAvatarId(fileInfo.getId());

        session.setAttribute("user", userDto);
        response.sendRedirect("/files/" + fileInfo.getId());
    }
}
