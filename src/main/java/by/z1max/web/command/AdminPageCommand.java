package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.UserService;

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
