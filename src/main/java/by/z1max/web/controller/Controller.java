package by.z1max.web.controller;

import by.z1max.util.AppContext;
import by.z1max.web.command.Command;
import by.z1max.web.command.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front")
public class Controller extends HttpServlet {

    private AppContext appContext;

    @Override
    public void init() throws ServletException {
        super.init();
        appContext = AppContext.getInstance();
    }

    @Override
    public void destroy() {
        super.destroy();
        appContext.destroy();
        appContext = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = getCommand(req.getParameter("command"));
        command.init(req, resp);
        command.process(appContext);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = getCommand(req.getParameter("command"));
        command.init(req, resp);
        command.process(appContext);
    }

    private Command getCommand(String command){
        try {
            Class type = Class.forName(String.format("by.z1max.web.command.%sCommand", command));
            return (Command) type.asSubclass(Command.class).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
           return new UnknownCommand();
        }
    }
}
