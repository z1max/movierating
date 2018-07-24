package by.z1max.web.command;

import by.z1max.util.AppContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutCommand extends Command {
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }
}
