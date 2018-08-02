package by.epam.web.command;

import by.epam.dto.ActiveUser;
import by.epam.exception.ServiceException;
import by.epam.model.User;
import by.epam.service.UserService;

public class DeleteAccountCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();

        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");
        String enteredPassword = wrapper.getParameter("password");

        try {
            User user = service.loadUserByIdAndPassword(activeUser.getId(), enteredPassword);
            service.delete(user);
            return CommandResponse.newBuilder()
                    .setTarget("/")
                    .setRedirect(true)
                    .setInvalidate(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
