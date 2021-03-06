package co.net.quiron.util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class FormManager {
    /**
     * Constructor for FormValidator
     */
    public FormManager() {

    }

    /**
     * Validates that at least one parameter has any value.
     *
     * @param request The HttpRequest.
     * @param requiredFields List with the required fields to validate.
     * @return whether the form is a valid form or not
     */
    public static boolean validForm(HttpServletRequest request, List<String> requiredFields) {

        boolean anyValue = false;

        for (Map.Entry parameter : request.getParameterMap().entrySet()) {

            String paramName = (String)parameter.getKey();
            anyValue = true;

            if (requiredFields.contains(paramName)
                    && (request.getParameter(paramName) == null
                    || request.getParameter(paramName).isEmpty())) {
                anyValue = false;
                break;
            }
        }
        return anyValue;
    }

    /**
     * Handles the null values of the request parameters.
     *
     * @param parameterValue Value of the respective parameter.
     * @return The value of the parameter or empty string if it's null.
     */
    public static String getValue(String parameterValue) {

        String value = "";

        if (parameterValue != null && !parameterValue.isEmpty()) {
            value = parameterValue;
        }
        return value;
    }

    /**
     * Handles the null values of the request parameters.
     *
     * @param parameterValue Value of the respective parameter.
     * @return The value of the parameter or zero (0) if it's null.
     */
    public static String getNumericValue(String parameterValue) {

        String value = "0";

        if (parameterValue != null && !parameterValue.isEmpty()) {
            value = parameterValue;
        }
        return value;
    }

}
