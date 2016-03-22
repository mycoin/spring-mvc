package org.ionnic.common.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.config.ConfigConstants;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
@SuppressWarnings("serial")
public class DefaultWebException extends RuntimeException implements ConfigConstants {

    @SuppressWarnings("unused")
    private final Log log = LogFactory.getLog(getClass());

    private String statusText;

    private int statusCode;

    private Throwable innerException = null;

    /**
     * @param status
     * @param statusInfo
     */
    public DefaultWebException( int status, String statusInfo ) {
        super(statusInfo);

        this.statusCode = status;
        this.statusText = statusInfo;
    }

    /**
     * @param status
     * @param statusInfo
     */
    public DefaultWebException( int status, String statusInfo, Throwable exception ) {
        super(statusInfo);

        this.statusCode = status;
        this.statusText = statusInfo;
        this.innerException = exception;
    }

    /**
     * @param data the data to set
     */
    public void setCause( Throwable data ) {
        this.innerException = data;
    }

    /**
     * @param mv
     * @param object
     */
    public void responseTo( ModelAndView mv, HttpServletResponse response ) {
        if (innerException == null) {
            mv.addObject(DATA_NAME, new ServletException("" + statusText));
        } else {
            mv.addObject(DATA_NAME, innerException);
        }

        mv.addObject(STATUS_NAME, statusCode);
        mv.addObject(STATUS_INFO_NAME, statusText);

        if (response != null) {
            response.setStatus(statusCode);
        }
    }
}