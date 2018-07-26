package by.z1max.web.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestWrapper {
    private Map<String, String[]> parameterMap;
    private Map<String, Object> attributeMap;
    private Map<String, Object> sessionAttributeMap;

    public void parseRequest(HttpServletRequest request){
        parameterMap = new HashMap<>(request.getParameterMap());
        attributeMap = new HashMap<>();
        sessionAttributeMap = new HashMap<>();

        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            attributeMap.put(key, request.getAttribute(key));
        }

        HttpSession session = request.getSession(false);
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();

        while (sessionAttributeNames.hasMoreElements()){
            String key = sessionAttributeNames.nextElement();
            sessionAttributeMap.put(key, session.getAttribute(key));
        }
    }

    public void updateRequest(HttpServletRequest request){
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry : attributeMap.entrySet()){
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        HttpSession session = request.getSession(false);
        for (Map.Entry<String, Object> entry : sessionAttributeMap.entrySet()){
            session.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    public String getParameter(String key){
        String[] values = parameterMap.get(key);
        if (values == null) {
            return null;
        }
        return values[0];
    }

    public String[] getParameterValues(String key){
        return parameterMap.get(key);
    }

    public void setAttribute(String key, Object value){
        attributeMap.put(key, value);
    }

    public void setSessionAttribute(String key, Object value){
        sessionAttributeMap.put(key, value);
    }

    public Object getSessionAttribute(String key){
        return sessionAttributeMap.get(key);
    }
}
