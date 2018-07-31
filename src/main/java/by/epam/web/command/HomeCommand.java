package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.MovieService;

public class HomeCommand extends Command{
    @Override
    public CommandResponse process() {
        MovieService service = appContext.getMovieService();

        try {
            wrapper.setAttribute("movies", service.getAll());
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("home")
                .build();
    }
}
