package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.util.AppContext;
import by.z1max.dto.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteAccountCommand extends Command {
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        UserService service = appContext.getUserService();

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
