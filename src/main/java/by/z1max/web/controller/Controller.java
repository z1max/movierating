package by.z1max.web.controller;

import by.z1max.util.Context;
import by.z1max.util.db.ConnectionPool;
import by.z1max.util.db.DataSource;
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

    private Context context;

    @Override
    public void init() throws ServletException {
        super.init();
        context = Context.getInstance();
    }

    @Override
    public void destroy() {
        super.destroy();
        context.destroy();
        context = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = getCommand(req.getParameter("command"));
        command.init(req, resp);
        command.process(context);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = getCommand(req.getParameter("command"));
        command.init(req, resp);
        command.process(context);
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
