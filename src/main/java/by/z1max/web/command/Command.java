package by.z1max.web.command;

import by.z1max.util.AppContext;
import by.z1max.web.wrapper.RequestWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    protected RequestWrapper wrapper;
    protected AppContext appContext;
    protected String method;

    public void init(RequestWrapper wrapper, AppContext appContext, String method){
        this.wrapper = wrapper;
        this.appContext = appContext;
        this.method = method;
    }

    public abstract CommandResponse process();

//    protected void forward(String target) throws ServletException, IOException {
//        target = String.format("pages/%s.jsp", target);
//        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
//        dispatcher.forward(request, response);
//    }
}
