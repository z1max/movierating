package by.epam.web.command;

import by.epam.exception.ServiceException;

public class DeleteMovieCommand extends Command {
    @Override
    public CommandResponse process() {
        int id = Integer.valueOf(wrapper.getParameter("movieId"));
        try {
            appContext.getMovieService().delete(id);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=Home")
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
