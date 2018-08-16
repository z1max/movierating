package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.UserService;

public class DeleteUserCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();
        int userId = Integer.parseInt(wrapper.getParameter("userId"));

        try {
            service.delete(userId);
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("/front?command=AdminPage")
                .setRedirect(true)
                .build();
    }
}
