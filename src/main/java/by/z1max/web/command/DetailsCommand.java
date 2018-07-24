package by.z1max.web.command;

import by.z1max.dto.ActiveUser;
import by.z1max.dto.EagerMovie;
import by.z1max.exception.ServiceException;
import by.z1max.service.MovieService;
import by.z1max.service.RatingService;

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
