package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.UserService;

public class AdminPageCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();
        try {
            wrapper.setAttribute("users", service.getAll());
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("admin")
                .build();
    }
}
