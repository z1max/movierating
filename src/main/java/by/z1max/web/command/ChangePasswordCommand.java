package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.util.Context;
import by.z1max.util.PasswordEncoder;
import by.z1max.web.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class ChangePasswordCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        UserService service = context.getUserService();
        PasswordEncoder passwordEncoder = context.getPasswordEncoder();

        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        String enteredPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");

        try {
            User user = service.get(activeUser.getId());
            if (!user.getPassword().equals(passwordEncoder.encode(enteredPassword))){
                throw new ServiceException("Password is incorrect!");
            }
            user.setPassword(newPassword);
            service.save(user);
            response.sendRedirect("");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
