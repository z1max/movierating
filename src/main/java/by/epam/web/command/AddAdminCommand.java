package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.UserService;

public class AddAdminCommand extends Command {
    @Override
    public CommandResponse process() {
        UserService service = appContext.getUserService();
        int id = Integer.valueOf(wrapper.getParameter("userId"));

        try {
            service.grantAdminAuthority(id);
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
