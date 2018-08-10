package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.UserService;

public class GrantOrDenyAdminCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();
        int id = Integer.valueOf(wrapper.getParameter("userId"));

        try {
            service.grantOrDenyAdminAuthority(id);
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
