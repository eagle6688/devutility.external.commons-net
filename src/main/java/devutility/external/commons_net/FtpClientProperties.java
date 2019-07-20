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
	/**
	 * ftp or sftp.
	 */
	private String protocol;

	/**
	 * Host address for Ftp server.
	 */
	private String host;

	/**
	 * Port number for Ftp server.
	 */
	private int port = 21;

	/**
	 * Login name for Ftp server.
	 */
	private String userName;

	/**
	 * Password for Ftp server.
	 */
	private String password;

	/**
	 * Whether Ftp server should enable passive mode or not?
	 */
	private boolean passiveMode;

	/**
	 * Encoding type for Ftp transmission.
	 */
	private String encoding = "UTF-8";

	/**
	 * Ftp connection timeout in milliseconds.
	 */
	private int connectTimeout;

	/**
	 * Internal buffer size for buffered data streams.
	 */
	private int bufferSize = 1024;

	/**
	 * Sets the file type to be transferred. This should be one of <code>FTP.ASCII_FILE_TYPE</code>,
	 * <code>FTP.BINARY_FILE_TYPE</code>, etc. The default file type is <code>FTP.ASCII_FILE_TYPE</code>.
	 */
	private int fileType;

	/**
	 * Start path of Ftp server catalog.
	 */
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

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
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