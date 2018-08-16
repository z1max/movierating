package by.epam.web.filter;

import by.epam.dto.ActiveUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/front"})
public class SecurityFilter implements Filter {
    private static final String[] RESTRICTED_FOR_USERS =
            {
                    "AdminPage",
                    "DeleteMovie",
                    "EditMovie",
                    "GrantOrDenyAdmin",
                    "DeleteReview",
                    "EnableOrDisableUser",
                    "AddGenreOrCountryCommand",
                    "DeleteGenreOrCountryCommand",
                    "GenresOrCountriesCommand",
                    "EditGenreOrCountryCommand"
            };
    private final static String[] ALLOWED_FOR_GUESTS =
            {
                    "ChangeLanguage",
                    "Details",
                    "Home",
                    "Signin",
                    "Signup",
                    "Unknown"
            };

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        boolean authorized = activeUser != null;
        String command = request.getParameter("command");
        if (command == null && request.getParameter("language") != null){
            command = "ChangeLanguage";
        }
        if (!authorized && !hasAuthority(command, ALLOWED_FOR_GUESTS)){
            request.getRequestDispatcher("pages/signin.jsp").forward(request, servletResponse);
            return;
        }
        if (authorized && !activeUser.isAdmin() && hasAuthority(command, RESTRICTED_FOR_USERS)){
            request.setAttribute("errorMessageKey", "exception.accessDenied");
            request.getRequestDispatcher("pages/error.jsp").forward(request, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    private boolean hasAuthority(String command, String[] allowed){
        for (String next : allowed){
            if (next.equals(command)){
                return true;
            }
        }
        return false;
    }
}
