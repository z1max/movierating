package by.z1max.web.command;

import by.z1max.dto.EagerMovie;
import by.z1max.exception.ServiceException;
import by.z1max.service.MovieService;
import by.z1max.service.RatingService;
import by.z1max.util.Context;
import by.z1max.web.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class DetailsCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        RatingService ratingService = context.getRatingService();
        MovieService movieService = context.getMovieService();

        int movieId = Integer.valueOf(request.getParameter("id"));
        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");

        try {
            EagerMovie movie = movieService.get(movieId);
            request.setAttribute("movie", movie);
            if (activeUser != null){
                byte rating = ratingService.getById(activeUser.getId(), movieId).getRating();
                request.setAttribute("userRating", rating);
            }
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", e);
        }
        forward("details");
    }
}
