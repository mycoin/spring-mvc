package org.ionnic.common.support;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ionnic.common.Config;
import org.ionnic.common.support.securty.DefaultCrypt;
import org.ionnic.common.util.WebUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class ContentTypeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {

        if (response.getContentType() == null) {
            response.setContentType(Config.CONTENT_TYPE);
            response.setCharacterEncoding(Config.CHARSET);
        }
    }

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(Config.CHARSET);
        }

        HttpSession session = request.getSession(true);
        String sessionId = session.getId();
        DefaultCrypt crypt = new DefaultCrypt(sessionId);

        Cookie cookie = new Cookie("SID", crypt.encrypt(sessionId + WebUtils.getRemoteAddr(request)));
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24 * 365);

        response.addCookie(cookie);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
        response.addHeader("Access-Control-Max-Age", "36000000");
        return true;
    }
}
