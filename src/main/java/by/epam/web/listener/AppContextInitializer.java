package by.epam.web.listener;

import by.epam.util.AppContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextInitializer implements ServletContextListener {
    private AppContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ctx = AppContext.getInstance();
        servletContextEvent.getServletContext().setAttribute("appContext", ctx);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ctx.destroy();
        ctx = null;
    }
}
