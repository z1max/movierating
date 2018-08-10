package by.epam.web.command;

import by.epam.exception.ServiceException;

public class EnableOrDisableUserCommand extends Command {
    @Override
    public CommandResponse process() {
        int id = Integer.valueOf(wrapper.getParameter("userId"));

        try {
            appContext.getUserService().enableOrDisable(id);
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
