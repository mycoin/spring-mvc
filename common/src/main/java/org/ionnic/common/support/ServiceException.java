package org.ionnic.common.support;

import javax.servlet.ServletException;

import org.ionnic.common.model.JSONObject;

/**
 * @author apple
 *
 */
public class ServiceException extends ServletException {

    private static final long serialVersionUID = 1L;

    private JSONObject result;

    /**
     * @param status
     * @param statusInfo
     */
    public ServiceException(int status, String statusInfo) {
        result = new JSONObject();

        result.setStatus(status);
        result.setStatusInfo(statusInfo);
    }

    /**
     * @param statusInfo
     */
    public ServiceException(String statusInfo) {
        this(500, statusInfo);
    }

    /**
     * @return
     */
    public JSONObject getObject() {
        return result;
    }

    /**
     * @param exception
     */
    public void setException(Exception exception) {
        if (null == exception) {
            return;
        }

        result.addAttribute("message", exception.getMessage());
        result.addAttribute("error", exception);
    }

    @Override
    public String toString() {
        return result.toString();
    }
}