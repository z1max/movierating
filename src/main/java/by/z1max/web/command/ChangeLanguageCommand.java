package by.z1max.web.command;

import by.z1max.util.Context;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        session.setAttribute("language", language);
        response.sendRedirect(request.getContextPath());
    }
}
