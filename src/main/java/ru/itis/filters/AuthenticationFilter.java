package ru.itis.filters;

import ru.itis.services.SignInService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private SignInService signInService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        this.signInService = (SignInService) context.getAttribute("signInService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        boolean isAuthenticated = false;
        boolean sessionExists = session != null;
        boolean isRequestOnAuthPage = request.getRequestURI().startsWith(request.getContextPath()+"/sign-in") ||
                request.getRequestURI().startsWith(request.getContextPath()+"/sign-up") ;

        if (sessionExists) {
            isAuthenticated = session.getAttribute("user")!= null;
        }


        if (isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage) {
            filterChain.doFilter(request, response);
            return;
        } else if (isAuthenticated && isRequestOnAuthPage) {
            response.sendRedirect(request.getContextPath()+"/profile");
        } else {
            response.sendRedirect(request.getContextPath()+"/sign-in");
        }
    }

    @Override
    public void destroy() {

    }
}
