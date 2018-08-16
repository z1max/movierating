package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.ReviewService;

public class DeleteReviewCommand extends Command {
    @Override
    public CommandResponse process() {
        ReviewService reviewService = appContext.getReviewService();

        int reviewId = Integer.parseInt(wrapper.getParameter("reviewId"));
        int movieId = Integer.parseInt(wrapper.getParameter("movieId"));

        try {
            reviewService.delete(reviewId);
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("/front?command=Details&id=" + movieId)
                .setRedirect(true)
                .build();
    }
}
