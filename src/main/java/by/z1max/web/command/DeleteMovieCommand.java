package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.util.AppContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteMovieCommand extends Command {
    @Override
    public CommandResponse process() {
        int id = Integer.valueOf(wrapper.getParameter("movieId"));
        try {
            appContext.getMovieService().delete(id);
            return CommandResponse.newBuilder()
                    .setTarget("front?command=Home")
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
