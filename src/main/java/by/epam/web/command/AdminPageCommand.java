package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.model.User;
import by.epam.service.UserService;

import java.util.List;

public class AdminPageCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();
        try {
            List<User> allUsers = service.getAll();
            wrapper.setAttribute("users", allUsers);
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
        }
        return CommandResponse.newBuilder()
                .setTarget("admin")
                .build();
    }
}
