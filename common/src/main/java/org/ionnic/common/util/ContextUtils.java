package org.ionnic.common.util;import javax.servlet.ServletContext;import org.springframework.beans.BeansException;import org.springframework.core.io.Resource;import org.springframework.web.context.ContextLoader;import org.springframework.web.context.WebApplicationContext;import org.springframework.web.context.support.WebApplicationContextUtils;/** * @author apple * */public abstract class ContextUtils extends WebApplicationContextUtils{    /**     * @param name     * @param requiredType     * @return     * @throws BeansException     */    public static <T> T getBean(Class<T> requiredType) throws BeansException {        return getContext().getBean(requiredType);    }    /**     * @param name     * @return     */    public static Object getBean(String name) {        return getContext().getBean(name);    }    /**     * @param name     * @param requiredType     * @return     */    public static <T> T getBean(String name, Class<T> requiredType) {        return getContext().getBean(name, requiredType);    }    /**     * @return     */    public static WebApplicationContext getContext() {        return ContextLoader.getCurrentWebApplicationContext();    }    /**     * @param location     * @return     */    public static Resource getResource(String location) {        return getContext().getResource(location);    }    /**     * @return     */    public static ServletContext getServletContext() {        return getContext().getServletContext();    }}