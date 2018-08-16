package by.epam.web.command;

import by.epam.dto.ActiveUser;
import by.epam.exception.ServiceException;
import by.epam.model.Review;
import by.epam.service.ReviewService;

@SuppressWarnings("Duplicates")
public class WriteReviewCommand extends Command {
    @Override
    public CommandResponse process() {
        ReviewService service = appContext.getReviewService();

        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");
        String comment = wrapper.getParameter("review");
        int movieId = Integer.parseInt(wrapper.getParameter("movieId"));
        Review review = new Review(activeUser.getId(), movieId, comment);

        try {
            service.create(review);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=Details&id=" + movieId)
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardError();
        }
    }
}
