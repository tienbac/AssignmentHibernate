package filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GuestFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filtering by Guest Filter !");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        String username = (String) httpSession.getAttribute("LogUsername");
        String role = (String) httpSession.getAttribute("LogRole");
        if (username!=null && username.length()>0 && role.equalsIgnoreCase("guest")){
            httpServletRequest.setAttribute("loggedIn", username);
            httpServletRequest.setAttribute("role", role);
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            ((HttpServletResponse) servletResponse).sendRedirect("login");
        }
    }

    public void destroy() {

    }
}
