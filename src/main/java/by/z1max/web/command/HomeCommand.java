package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.MovieService;

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
