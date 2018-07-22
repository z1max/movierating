package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.util.Context;
import by.z1max.util.PasswordEncoder;
import by.z1max.web.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteAccountCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        UserService service = context.getUserService();

        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        String enteredPassword = request.getParameter("password");

        try {
            User user = service.loadUserByIdAndPassword(activeUser.getId(), enteredPassword);
            service.delete(user);
            response.sendRedirect(request.getContextPath());
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
            forward("unknown");
        }
    }
}
