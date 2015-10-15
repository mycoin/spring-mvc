package net.io.config;import java.util.UUID;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpSession;import net.io.config.support.CsrfToken;/** * @author apple * */public abstract class Security {	public static final String SESSION_NAME = CsrfToken.class.getName() + ".CSRF_TOKEN";	public static final String HEADER_NAME = "X-Requested-Token";	public static final String PARAMETER_NAME = "token";	/**	 * @param request	 * @param session	 * @return	 */	public static boolean checkToken(HttpServletRequest request) {		CsrfToken csrfToken = loadToken(request);		if (csrfToken == null) {			return false;		} else {			String tokenValue = request.getHeader(csrfToken.getHeaderName());			if (tokenValue == null) {				tokenValue = request.getParameter(csrfToken.getParameterName());			}			return csrfToken.getToken().equals(tokenValue);		}	}	/**	 * @param request	 * @param forceUpdate	 *	 * @return	 */	public static CsrfToken generateToken(HttpServletRequest request) {		CsrfToken token = loadToken(request);		if (token == null) {			token = new CsrfToken(HEADER_NAME, PARAMETER_NAME, "" + UUID.randomUUID());			HttpSession session = request.getSession(true);			session.setAttribute(SESSION_NAME, token);		}		return token;	}	/**	 * @param request	 * @return	 */	public static CsrfToken loadToken(HttpServletRequest request) {		HttpSession session = request.getSession(true);		Object token = session.getAttribute(SESSION_NAME);		if (token == null) {			return null;		} else {			return (CsrfToken) token;		}	}	/**	 * @param request	 */	public static void removeToken(HttpServletRequest request) {		HttpSession session = request.getSession(true);		session.removeAttribute(SESSION_NAME);	}}