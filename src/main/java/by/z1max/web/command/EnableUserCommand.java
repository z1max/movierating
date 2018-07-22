package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.UserService;
import by.z1max.util.Context;

import javax.servlet.ServletException;
import java.io.IOException;

public class EnableUserCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        UserService service = context.getUserService();
        int id = Integer.valueOf(request.getParameter("userId"));

        try {
            service.enable(id);
            response.sendRedirect(request.getContextPath() + "/front?command=AdminPage");
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
            forward("unknown");
        }
    }
}
