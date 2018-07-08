package by.z1max.web.command;

import by.z1max.dao.*;
import by.z1max.exception.ServiceException;
import by.z1max.service.MovieService;
import by.z1max.service.MovieServiceImpl;
import by.z1max.util.db.DataSource;

import javax.servlet.ServletException;
import java.io.IOException;

public class HomeCommand extends Command{
    @Override
    public void process(DataSource dataSource) throws ServletException, IOException {
        MovieDao movieDao = new MovieDaoImpl(dataSource);
        RatingDao ratingDao = new RatingDaoImpl(dataSource);
        ReviewDao reviewDao = new ReviewDaoImpl(dataSource);

        MovieService service = new MovieServiceImpl(movieDao, ratingDao, reviewDao);

        try {
            request.setAttribute("movies", service.getAll());
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", e);
        }
        forward("home");
    }
}
