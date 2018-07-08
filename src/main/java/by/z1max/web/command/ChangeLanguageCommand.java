package by.z1max.web.command;

import by.z1max.util.db.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand extends Command {
    @Override
    public void process(DataSource dataSource) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        session.setAttribute("language", language);
        response.sendRedirect("index.jsp");
    }
}
