package by.z1max.web.command;

import by.z1max.dto.EagerMovie;
import by.z1max.exception.ServiceException;
import by.z1max.service.MovieService;
import by.z1max.service.RatingService;
import by.z1max.util.AppContext;
import by.z1max.dto.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class DetailsCommand extends Command {
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        RatingService ratingService = appContext.getRatingService();
        MovieService movieService = appContext.getMovieService();

        int movieId = Integer.valueOf(request.getParameter("id"));
        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");

        try {
            EagerMovie movie = movieService.getEager(movieId);
            request.setAttribute("movie", movie);
            if (activeUser != null){
                byte rating = ratingService.getById(activeUser.getId(), movieId).getRating();
                request.setAttribute("userRating", rating);
            }
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
        }
        forward("details");
    }
}
