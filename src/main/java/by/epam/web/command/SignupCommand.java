package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.model.User;
import by.epam.service.UserService;

public class SignupCommand extends Command {
    @Override
    public CommandResponse process() {
        if ("GET".equals(method)) {
            return CommandResponse.newBuilder()
                    .setTarget("signup")
                    .build();
        } else {
            UserService service = appContext.getUserService();

            String username = wrapper.getParameter("username");
            String email = wrapper.getParameter("email");
            String password = wrapper.getParameter("password");
            User user = new User(username, email, password);
            try {
                service.save(user);
                return CommandResponse.newBuilder()
                        .setTarget("signin")
                        .build();
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
                wrapper.setAttribute("errorParam", "'" + username + "/" + email + "'");
                return CommandResponse.forwardError();
            }
        }
    }
}
