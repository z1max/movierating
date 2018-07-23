package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.util.Context;
import by.z1max.dto.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class SigninCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        String method = request.getMethod();
        if ("GET".equals(method)){
            forward("signin");
        }
        if ("POST".equals(method)){
            UserService service = context.getUserService();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            try {
                User user = service.loadUserByEmailAndPassword(email, password);
                ActiveUser activeUser = new ActiveUser(user.getId(), user.getUsername(), user.getRoles(), user.getStatus());
                request.getSession(false).setAttribute("activeUser", activeUser);
                response.sendRedirect(request.getContextPath() + "/front?command=Home");
            } catch (ServiceException e) {
                request.setAttribute("errorMessageKey", e.getMessage());
                forward("signin");
            }
        }
    }
}
