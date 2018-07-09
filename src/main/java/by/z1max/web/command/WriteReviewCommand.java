package by.z1max.web.command;

import by.z1max.dao.ReviewDao;
import by.z1max.dao.ReviewDaoImpl;
import by.z1max.exception.ServiceException;
import by.z1max.model.Review;
import by.z1max.service.ReviewService;
import by.z1max.service.ReviewServiceImpl;
import by.z1max.util.Context;
import by.z1max.web.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class WriteReviewCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        ReviewService service = context.getReviewService();

        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        String comment = request.getParameter("review");
        int movieId = Integer.valueOf(request.getParameter("movieId"));
        Review review = new Review(null, activeUser.getId(), null, movieId, comment, null);

        try {
            service.create(review);
            response.sendRedirect("front?command=Details&id=" + movieId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
