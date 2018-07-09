package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.MovieService;
import by.z1max.util.Context;

import javax.servlet.ServletException;
import java.io.IOException;

public class HomeCommand extends Command{
    @Override
    public void process(Context context) throws ServletException, IOException {
        MovieService service = context.getMovieService();

        try {
            request.setAttribute("movies", service.getAll());
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", e);
        }
        forward("home");
    }
}
