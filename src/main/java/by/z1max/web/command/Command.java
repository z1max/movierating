package by.z1max.web.command;

import by.z1max.util.db.DataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(HttpServletRequest request, HttpServletResponse response){
        this.response = response;
        this.request = request;
    }

    public abstract void process(DataSource dataSource) throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("pages/%s.jsp", target);
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
