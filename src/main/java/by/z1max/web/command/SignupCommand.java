package by.z1max.web.command;

import by.z1max.dao.UserDao;
import by.z1max.dao.UserDaoImpl;
import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.service.UserService;
import by.z1max.service.UserServiceImpl;
import by.z1max.util.Context;
import by.z1max.util.PasswordEncoder;

import javax.servlet.ServletException;
import java.io.IOException;

public class SignupCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")) {
            forward("signup");
        }
        if (method.equals("POST")){
            UserService service = context.getUserService();

            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = new User(null, username, email, password, null, null, true);

            try {
                service.save(user);
                forward("signin");
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", e);
                forward("signup");
            }
        }
    }
}