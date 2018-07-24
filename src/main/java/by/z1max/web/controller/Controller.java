package by.z1max.web.controller;

import by.z1max.util.AppContext;
import by.z1max.web.command.Command;
import by.z1max.web.command.CommandResponse;
import by.z1max.web.command.UnknownCommand;
import by.z1max.web.wrapper.RequestWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Command command = getCommand(req.getParameter("command"));
        AppContext ctx = (AppContext) getServletContext().getAttribute("appContext");
        RequestWrapper wrapper = new RequestWrapper();
        wrapper.parseRequest(req);

        command.init(wrapper, ctx, req.getMethod());
        CommandResponse commandResponse = command.process();

        wrapper.updateRequest(req);

        handleCommandResponse(commandResponse, req, resp);
    }

    private Command getCommand(String command){
        try {
            Class type = Class.forName(String.format("by.z1max.web.command.%sCommand", command));
            return (Command) type.asSubclass(Command.class).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
           return new UnknownCommand();
        }
    }

    private void handleCommandResponse(CommandResponse commandResponse, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (commandResponse.isRedirect()){
            resp.sendRedirect(req.getContextPath() + commandResponse.getTarget());
        } else {
            String target = String.format("pages/%s.jsp", commandResponse.getTarget());
            req.getRequestDispatcher(target).forward(req, resp);
        }
    }
}
