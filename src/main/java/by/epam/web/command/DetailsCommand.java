package by.epam.web.command;

import by.epam.dto.ActiveUser;
import by.epam.dto.EagerMovie;
import by.epam.exception.ServiceException;
import by.epam.service.MovieService;
import by.epam.service.RatingService;

public class DetailsCommand extends Command {
    @Override
    public CommandResponse process() {
        RatingService ratingService = appContext.getRatingService();
        MovieService movieService = appContext.getMovieService();

        int movieId = Integer.valueOf(wrapper.getParameter("id"));
        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");

        try {
            EagerMovie movie = movieService.getEager(movieId);
            wrapper.setAttribute("movie", movie);
            if (activeUser != null){
                byte rating = ratingService.getById(activeUser.getId(), movieId).getRating();
                wrapper.setAttribute("userRating", rating);
            }
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("details")
                .build();
    }
}
