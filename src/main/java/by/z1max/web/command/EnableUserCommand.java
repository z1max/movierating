package by.z1max.web.command;

import by.z1max.exception.ServiceException;

public class EnableUserCommand extends Command {
    @Override
    public CommandResponse process() {
        int id = Integer.valueOf(wrapper.getParameter("userId"));

        try {
            appContext.getUserService().enable(id);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=AdminPage")
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
