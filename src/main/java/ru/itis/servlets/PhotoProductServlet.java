package ru.itis.servlets;

import ru.itis.models.FileInfo;
import ru.itis.models.Product;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-photo")
@MultipartConfig
public class PhotoProductServlet extends HttpServlet {

    private FileService fileService;

    @Override
    public void init(ServletConfig config) {
        this.fileService = (FileService) config.getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "http://localhost:8080/add-photo";
        request.setAttribute("path", path);
        request.getRequestDispatcher("fileUpload.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");

        HttpSession session = request.getSession(true);
        Product product = (Product) session.getAttribute("saveProduct");

        FileInfo fileInfo = fileService.saveFileToStorageProduct(
                product,
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                (int) part.getSize());

        product.setPictureId(fileInfo.getId());

        session.setAttribute("saveProduct", product);

        response.sendRedirect("/files/" + fileInfo.getId());
    }
}
