package by.z1max.web.command;

import by.z1max.dto.ActiveUser;
import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;

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
            return CommandResponse.forwardUnknown();
        }
    }
}
