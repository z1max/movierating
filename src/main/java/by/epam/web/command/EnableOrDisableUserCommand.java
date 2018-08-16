package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.UserService;

public class EnableOrDisableUserCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService userService = appContext.getUserService();
        int id = Integer.parseInt(wrapper.getParameter("userId"));

        try {
            userService.enableOrDisable(id);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=AdminPage")
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardError();
        }
    }
}
