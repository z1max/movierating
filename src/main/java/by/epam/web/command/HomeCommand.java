package by.epam.web.command;

import by.epam.dto.LazyMovie;
import by.epam.exception.ServiceException;
import by.epam.service.MovieService;

import java.util.List;

public class HomeCommand extends Command{
    @Override
    public CommandResponse process() {
        MovieService service = appContext.getMovieService();

        try {
            List<LazyMovie> movies = service.getAll();
            wrapper.setAttribute("movies", movies);
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("home")
                .build();
    }
}
