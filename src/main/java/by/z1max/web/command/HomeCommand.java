package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.MovieService;
import by.z1max.util.AppContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class HomeCommand extends Command{
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        MovieService service = appContext.getMovieService();

        try {
            request.setAttribute("movies", service.getAll());
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
        }
        forward("home");
    }
}
