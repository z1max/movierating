package by.z1max.web.listener;

import by.z1max.web.ActiveUser;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LoginLogoutListener implements HttpSessionListener {
    private final static Logger LOG = Logger.getLogger(LoginLogoutListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ActiveUser activeUser = (ActiveUser) httpSessionEvent.getSession().getAttribute("activeUser");
        LOG.info("Session created " + activeUser);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ActiveUser activeUser = (ActiveUser) httpSessionEvent.getSession().getAttribute("activeUser");
        LOG.info("Session destroyed " + activeUser);
        activeUser = null;
    }
}
