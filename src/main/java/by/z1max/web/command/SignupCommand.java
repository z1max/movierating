package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.util.AppContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class SignupCommand extends Command {
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        String method = request.getMethod();
        if ("GET".equals(method)) {
            forward("signup");
        }
        if ("POST".equals(method)){
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = new User(username, email, password);
            try {
                appContext.getUserService().save(user);
                forward("signin");
            } catch (ServiceException e) {
                request.setAttribute("errorMessageKey", e.getMessage());
                request.setAttribute("errorParam", "'" + username + "/" + email + "'");
                forward("signup");
            }
        }
    }
}
