package by.z1max.web.command;

import by.z1max.dto.ActiveUser;
import by.z1max.exception.ServiceException;
import by.z1max.model.Rating;
import by.z1max.service.RatingService;

public class RateCommand extends Command {
    @Override
    public CommandResponse process() {
        RatingService service = appContext.getRatingService();

        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");
        byte enteredRating = Byte.valueOf(wrapper.getParameter("rating"));
        int movieId = Integer.valueOf(wrapper.getParameter("movieId"));
        Rating rating = new Rating(activeUser.getId(), movieId, enteredRating);

        try {
            service.create(rating);
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
