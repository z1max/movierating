package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.service.UserService;

public class AddAdminCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();
        int id = Integer.valueOf(wrapper.getParameter("userId"));

        try {
            service.grandAdminAuthority(id);
            return CommandResponse.newBuilder()
                    .setTarget("/front?command=AdminPage")
                    .setRedirect(true)
                    .build();
        } catch (ServiceException e) {
            wrapper.setAttribute("errorMessageKey", e.getMessage());
            return CommandResponse.forwardUnknown();
        }
    }
}
