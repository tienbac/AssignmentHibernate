package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filtering by Admin Filter !");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        String username = (String) httpSession.getAttribute("logUsername");
        String role = (String) httpSession.getAttribute("logRole");
        if (username!=null && username.length() > 0 && role.equalsIgnoreCase("admin")){
            httpServletRequest.setAttribute("LoggedIn", username);
            httpServletRequest.setAttribute("role", role);
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    public void destroy() {

    }
}
