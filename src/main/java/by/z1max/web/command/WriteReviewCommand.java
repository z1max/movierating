package by.z1max.web.command;

import by.z1max.dto.ActiveUser;
import by.z1max.exception.ServiceException;
import by.z1max.model.Review;

public class WriteReviewCommand extends Command {
    @Override
    public CommandResponse process() {
        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");
        String comment = wrapper.getParameter("review");
        int movieId = Integer.valueOf(wrapper.getParameter("movieId"));
        Review review = new Review(activeUser.getId(), movieId, comment);

        try {
            appContext.getReviewService().create(review);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=Details&id=" + movieId)
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
