package org.ionnic.core.support.config;

/**
 * 过滤器配置信息
 * 
 * @author apple
 */
public class FilterConfig {

	private boolean ignoreMethod = true;

	private String methodField = "method";

	private String charset = "UTF-8";

	private boolean forceEncoding = false;

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @return the methodField
	 */
	public String getMethodField() {
		return methodField;
	}

	/**
	 * @return the forceEncoding
	 */
	public boolean isForceEncoding() {
		return forceEncoding;
	}

	/**
	 * @return the ignoreMethod
	 */
	public boolean isIgnoreMethod() {
		return ignoreMethod;
	}

	/**
	 * @param charset
	 *            the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @param forceEncoding
	 *            the forceEncoding to set
	 */
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}

	/**
	 * @param ignoreMethod
	 *            the ignoreMethod to set
	 */
	public void setIgnoreMethod(boolean ignoreMethod) {
		this.ignoreMethod = ignoreMethod;
	}

	/**
	 * @param methodField
	 *            the methodField to set
	 */
	public void setMethodField(String methodField) {
		this.methodField = methodField;
	}
}
