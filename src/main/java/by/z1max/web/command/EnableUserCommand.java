package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.util.AppContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class EnableUserCommand extends Command {
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("userId"));

        try {
            appContext.getUserService().enable(id);
            response.sendRedirect(request.getContextPath() + "/front?command=AdminPage");
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
            forward("unknown");
        }
    }
}
