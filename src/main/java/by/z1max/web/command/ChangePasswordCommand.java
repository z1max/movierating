package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.util.Context;
import by.z1max.util.PasswordEncoder;
import by.z1max.dto.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class ChangePasswordCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        UserService service = context.getUserService();
        PasswordEncoder passwordEncoder = context.getPasswordEncoder();

        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        String enteredPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");

        try {
            User user = service.loadUserByIdAndPassword(activeUser.getId(), enteredPassword);
            user.setPassword(newPassword);
            service.save(user);
            response.sendRedirect("front?command=Profile&id=" + activeUser.getId());
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
            forward("unknown");
        }
    }
}
