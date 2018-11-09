package co.net.quiron.util.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Writes a bootstrap Alert Message according to the type of message provided.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Alert extends SimpleTagSupport {

    StringWriter text = new StringWriter();
    //private String text;
    private String type;
    private String url;

    /**
     * Default processing of the tag does nothing.
     *
     * @throws JspException Indicate an error occurred while processing this tag.
     * @throws IOException Throw IOException if there was an error writing to the output stream
     */
    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();

        JspWriter out = getJspContext().getOut();
        getJspBody().invoke(text);

        if(text != null && !text.toString().isEmpty()) {

            out.println("<div class=\"alert " + getAlertClass() + "\" role=\"alert\">" + text.toString() + "<br/>");

            if (url != null && !url.isEmpty()) {
                out.println("<a href=\""+ url + "\" class=\"alert-link\">CLICK HERE</a> to continue.");
            }

            out.println("</div>");
        }
    }

    /**
     * Gets alert bootstrap class.
     *
     * @return the alert class
     */
    protected String getAlertClass() {

        if(type == null && !type.isEmpty()){
            return "";
        }

        switch (type) {
            case "ERROR":
                return "alert-danger";

            case "WARNING":
                return "alert-warning";

            default:
                return "alert-info";
        }
    }
}
