package devutility.external.commons_net;

import devutility.internal.net.UrlUtils;

/**
 * 
 * FtpClientProperties
 * 
 * @author: Aldwin Su
 * @version: 2019-07-17 19:39:38
 */
public class FtpClientProperties {
	private String protocol;
	private String host;
	private int port = 21;
	private String userName;
	private String password;
	private boolean passiveMode;
	private String encoding = "UTF-8";
	private String start;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPassiveMode() {
		return passiveMode;
	}

	public void setPassiveMode(boolean passiveMode) {
		this.passiveMode = passiveMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * Get full path.
	 * @param tailPath Tail path of ftp.
	 * @return String
	 */
	public String getPath(String tailPath) {
		return UrlUtils.concat(start, tailPath);
	}
}