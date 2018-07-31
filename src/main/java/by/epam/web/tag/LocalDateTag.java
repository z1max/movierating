package by.epam.web.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocalDateTag extends TagSupport {
    private static final Logger LOG = Logger.getLogger(LocalDateTag.class);

    private LocalDate localDate;
    private Locale locale;

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setLocale(String language) {
        this.locale = new Locale(language);
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            String formatted = DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.MEDIUM)
                    .withLocale(locale)
                    .format(localDate);
            out.print(formatted);
        } catch (IOException e) {
            LOG.error("Error printing tag", e);
        }
        return SKIP_BODY;
    }
}
