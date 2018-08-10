package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.UserService;

public class DeleteUserCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService userService = appContext.getUserService();
        int userId = Integer.valueOf(wrapper.getParameter("userId"));

        try {
            userService.delete(userId);
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("/front?command=AdminPage")
                .setRedirect(true)
                .build();
    }
}
