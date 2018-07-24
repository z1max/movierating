package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.util.AppContext;
import by.z1max.dto.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class SigninCommand extends Command {
    @Override
    public CommandResponse process() {
        if ("GET".equals(method)){
            return CommandResponse.newBuilder()
                    .setTarget("signin")
                    .build();
        } else {
            UserService service = appContext.getUserService();
            String email = wrapper.getParameter("email");
            String password = wrapper.getParameter("password");
            try {
                User user = service.loadUserByEmailAndPassword(email, password);
                ActiveUser activeUser = new ActiveUser(user.getId(), user.getUsername(), user.getRoles(), user.getStatus());
                wrapper.setSessionAttribute("activeUser", activeUser);
                return CommandResponse.newBuilder()
                        .setTarget("/front?command=Home")
                        .setRedirect(true)
                        .build();
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
                return CommandResponse.newBuilder()
                        .setTarget("signin")
                        .build();
            }
        }
    }
}
