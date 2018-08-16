package by.epam.web.command;

import by.epam.dto.ActiveUser;
import by.epam.exception.ServiceException;
import by.epam.model.User;
import by.epam.service.UserService;

public class ProfileCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();
        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");

        try {
            User user = service.get(activeUser.getId());
            wrapper.setAttribute("user", user);
            return CommandResponse.newBuilder()
                    .setTarget("profile")
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardError();
        }
    }
}
