package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.UserService;
import by.z1max.util.AppContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class AdminPageCommand extends Command {
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        UserService service = appContext.getUserService();
        try {
            request.setAttribute("users", service.getAll());
            forward("admin");
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
            forward("admin");
        }
    }
}
