package by.z1max.web.command;

import by.z1max.dao.UserDao;
import by.z1max.dao.UserDaoImpl;
import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.service.UserServiceImpl;
import by.z1max.util.Context;
import by.z1max.util.PasswordEncoder;
import by.z1max.web.ActiveUser;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteAccountCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        PasswordEncoder passwordEncoder = context.getPasswordEncoder();
        UserService service = context.getUserService();

        ActiveUser activeUser = (ActiveUser) request.getSession(false).getAttribute("activeUser");
        String enteredPassword = request.getParameter("password");

        try {
            User user = service.get(activeUser.getId());
            if (!user.getPassword().equals(passwordEncoder.encode(enteredPassword))){
                throw new ServiceException("Password is incorrect!");
            }
            service.delete(user);
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", e);
        }
    }
}
