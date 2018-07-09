package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.Rating;
import by.z1max.service.RatingService;
import by.z1max.util.Context;
import by.z1max.web.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class RateCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        RatingService service = context.getRatingService();

        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        byte enteredRating = Byte.valueOf(request.getParameter("rating"));
        int movieId = Integer.valueOf(request.getParameter("movieId"));
        Rating rating = new Rating(activeUser.getId(), movieId, enteredRating);

        try {
            service.create(rating);
            response.sendRedirect(request.getContextPath() + "/front?command=Details&id=" + movieId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
