package by.epam.web.command;

import by.epam.exception.ServiceException;

public class DeleteReviewCommand extends Command {
    @Override
    public CommandResponse process() {
        int reviewId = Integer.valueOf(wrapper.getParameter("reviewId"));
        int movieId = Integer.valueOf(wrapper.getParameter("movieId"));
        try {
            appContext.getReviewService().delete(reviewId);
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("/front?command=Details&id=" + movieId)
                .setRedirect(true)
                .build();
    }
}
