package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.util.Context;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteMovieCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("movieId"));
        try {
            context.getMovieService().delete(id);
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
        }
        response.sendRedirect("front?command=Home");
    }
}