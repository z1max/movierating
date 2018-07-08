package by.z1max.web.command;

import by.z1max.dao.*;
import by.z1max.exception.ServiceException;
import by.z1max.service.*;
import by.z1max.util.db.DataSource;
import by.z1max.dto.EagerMovie;

import javax.servlet.ServletException;
import java.io.IOException;

public class DetailsCommand extends Command {
    @Override
    public void process(DataSource dataSource) throws ServletException, IOException {
        MovieDao movieDao = new MovieDaoImpl(dataSource);
        RatingDao ratingDao = new RatingDaoImpl(dataSource);
        ReviewDao reviewDao = new ReviewDaoImpl(dataSource);

        MovieService movieService = new MovieServiceImpl(movieDao, ratingDao, reviewDao);

        int id = Integer.valueOf(request.getParameter("id"));
        try {
            EagerMovie movie = movieService.get(id);
            request.setAttribute("movie", movie);
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", e);
        }
        forward("details");
    }
}
