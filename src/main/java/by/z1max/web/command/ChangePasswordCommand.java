package by.z1max.web.command;

import by.z1max.dto.ActiveUser;
import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;

public class ChangePasswordCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();

        ActiveUser activeUser = (ActiveUser) wrapper.getSessionAttribute("activeUser");
        String enteredPassword = wrapper.getParameter("old-password");
        String newPassword = wrapper.getParameter("new-password");

        try {
            User user = service.loadUserByIdAndPassword(activeUser.getId(), enteredPassword);
            user.setPassword(newPassword);
            service.save(user);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=Profile&id=" + activeUser.getId())
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
