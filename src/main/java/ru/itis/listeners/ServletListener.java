package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.dao.*;
import ru.itis.dao.impl.*;
import ru.itis.services.*;
import ru.itis.services.impl.*;
import ru.itis.services.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qazwsxedcrfv531";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrovka";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String IMAGES_STORAGE_PATH = "C:\\Photo\\photki\\";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        FileRepository fileRepository = new FileRepositoryImpl(dataSource);
        CategoryRepository categoryRepository = new CategoryRepositoryImpl(dataSource);
        ProductRepository productRepository = new ProductRepositoryImpl(dataSource);
        SupportRepository supportRepository = new SupportRepositoryImpl(dataSource);
        OrderRepository orderRepository = new OrderRepositoryImpl(dataSource);

        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        Validator validator = new ValidatorImpl(userRepository);
        SignUpService signUpService = new SignUpServiceImpl(userRepository, passwordEncoder, validator);
        SignInService signInService = new SignInServiceImpl(userRepository, passwordEncoder);
        FileService fileService = new FileServiceImpl(IMAGES_STORAGE_PATH, fileRepository, userRepository, productRepository);
        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);
        ProductService productService = new ProductServiceImpl(productRepository);
        SupportService supportService = new SupportServiceImpl(supportRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository);

        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("fileService", fileService);
        servletContext.setAttribute("categoryService",categoryService);
        servletContext.setAttribute("productService",productService);
        servletContext.setAttribute("supportService", supportService);
        servletContext.setAttribute("orderService", orderService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
