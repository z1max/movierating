package by.z1max.web.command;

import by.z1max.dto.ActiveUser;
import by.z1max.exception.ServiceException;
import by.z1max.model.Review;
import by.z1max.util.AppContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class WriteReviewCommand extends Command {
    @Override
    public void process(AppContext appContext) throws ServletException, IOException {
        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        String comment = request.getParameter("review");
        int movieId = Integer.valueOf(request.getParameter("movieId"));
        Review review = new Review(activeUser.getId(), movieId, comment);

        try {
            appContext.getReviewService().create(review);
            response.sendRedirect("front?command=Details&id=" + movieId);
        } catch (ServiceException e) {
            request.setAttribute("errorMessageKey", e.getMessage());
            forward("unknown");
        }
    }
}
