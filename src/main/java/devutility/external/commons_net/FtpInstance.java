package devutility.external.commons_net;

import devutility.internal.net.UrlUtils;

public class FtpInstance {
	private String protocol;
	private String host;
	private int port;
	private String userName;
	private String password;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getPath(String tailPath) {
		return UrlUtils.concat(start, tailPath);
	}
}