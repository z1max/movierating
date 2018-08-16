package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.MovieService;

public class DeleteMovieCommand extends Command {
    @Override
    public CommandResponse process() {
        MovieService service = appContext.getMovieService();
        int id = Integer.parseInt(wrapper.getParameter("movieId"));

        try {
            service.delete(id);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=Home")
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardError();
        }
    }
}
