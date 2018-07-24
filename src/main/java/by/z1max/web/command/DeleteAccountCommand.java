package by.z1max.web.command;

import by.z1max.dto.ActiveUser;
import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;

public class DeleteAccountCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();

        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");
        String enteredPassword = wrapper.getParameter("password");

        try {
            User user = service.loadUserByIdAndPassword(activeUser.getId(), enteredPassword);
            service.delete(user);
            return CommandResponse.forwardIndex();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
