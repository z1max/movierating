package by.epam.web.command;

import by.epam.util.AppContext;
import by.epam.web.wrapper.RequestWrapper;

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
}
