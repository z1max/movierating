package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.UserService;
import by.z1max.util.Context;

import javax.servlet.ServletException;
import java.io.IOException;

public class AdminPageCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        UserService service = context.getUserService();
        try {
            request.setAttribute("users", service.getAll());
            forward("admin");
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
            forward("admin");
        }
    }
}
