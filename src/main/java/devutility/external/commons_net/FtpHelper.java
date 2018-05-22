package devutility.external.commons_net;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import devutility.internal.lang.StringHelper;
import devutility.internal.net.UrlUtils;

public class FtpHelper implements Closeable {
	private String host;
	private int port;
	private String userName;
	private String password;
	private String startPath;
	protected FTPClient ftpClient;

	/**
	 * FtpHelper
	 * @param host: Ftp server host address.
	 * @param port: Ftp server port.
	 * @param userName: Ftp server login name.
	 * @param password: Ftp server password.
	 * @param startPath: The start path after ftp host, optional parameter, if no value set to null.
	 */
	public FtpHelper(String host, int port, String userName, String password, String startPath) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.startPath = startPath;
		init();
	}

	/**
	 * Initializing.
	 */
	private void init() {
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("utf-8");

		if (StringHelper.isNotEmpty(startPath)) {
			startPath = startPath.trim();

			if (startPath.endsWith("/")) {
				startPath = StringHelper.trimEnd(startPath, "/");
			}

			if (!startPath.startsWith("/")) {
				startPath = "/" + startPath;
			}
		}
	}

	/**
	 * Connect ftp server.
	 * @throws SocketException
	 * @throws IOException
	 */
	public void connect() throws SocketException, IOException {
		if (ftpClient.isConnected()) {
			return;
		}

		ftpClient.connect(host, port);
		ftpClient.login(userName, password);
		int replyCode = ftpClient.getReplyCode();

		if (!FTPReply.isPositiveCompletion(replyCode)) {
			System.out.println(String.format("Connection to ftp server %s:%d failed.", host, port));
		} else {
			System.out.println(String.format("Connection to ftp server %s:%d successed.", host, port));
		}
	}

	/**
	 * Disconnect ftp server.
	 */
	public void disconnect() {
		try {
			ftpClient.disconnect();
			System.out.println(String.format("Disconnect ftp server %s:%d successed.", host, port));
		} catch (IOException e) {
			System.out.println(String.format("Disconnect ftp server %s:%d failed.", host, port));
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		disconnect();
	}

	/**
	 * Create direcroty.
	 * @param path: Directory path.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean createDirecroty(String path) throws IOException {
		if (StringHelper.isNullOrEmpty(path)) {
			throw new IOException("Path cannot be null, create direcroty failed!");
		}

		if (!path.startsWith("/")) {
			path = "/" + path;
		}

		String tailPath = path;

		if (StringHelper.isNotEmpty(startPath)) {
			if (path.indexOf(startPath) != 0) {
				String message = String.format("path %s must start with start path %s, create direcroty failed!", path, startPath);
				throw new IOException(message);
			}

			tailPath = path.substring(startPath.length() + 1);
		}

		String[] array = tailPath.split("/");
		String creatingDirectory = startPath;

		for (int i = 0; i < array.length; i++) {
			creatingDirectory = UrlUtils.concat(creatingDirectory, array[i]);

			if (ftpClient.changeWorkingDirectory(creatingDirectory)) {
				continue;
			}

			if (!ftpClient.makeDirectory(creatingDirectory)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Check whether the file exists or not.
	 * @param filePath: File path after host address.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean isFileExist(String filePath) throws IOException {
		connect();

		if (ftpClient.listFiles(filePath).length > 0) {
			return true;
		}

		return false;
	}

	/**
	 * Upload stream to ftp server.
	 * @param inputStream: InputStream object.
	 * @param ftpDirectoryPath: The ftp directory without after host address.
	 * @param fileName: File name used on ftp server.
	 * @return boolean
	 * @throws SocketException
	 * @throws IOException
	 */
	public boolean upload(InputStream inputStream, String ftpDirectoryPath, String fileName) throws SocketException, IOException {
		if (!ftpDirectoryPath.startsWith("/")) {
			ftpDirectoryPath = "/" + ftpDirectoryPath;
		}

		if (!createDirecroty(ftpDirectoryPath)) {
			throw new IOException("Create direcroty failed!");
		}

		if (!ftpClient.changeWorkingDirectory(ftpDirectoryPath)) {
			throw new IOException("Change working direcroty failed!");
		}

		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		return ftpClient.storeFile(fileName, inputStream);
	}

	/**
	 * Upload file to ftp server.
	 * @param localFile: Local file.
	 * @param ftpDirectoryPath: The ftp directory without after host address.
	 * @param fileName: File name used on ftp server.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean upload(String localFile, String ftpDirectoryPath, String fileName) throws IOException {
		File file = new File(localFile);

		if (!file.exists()) {
			throw new IOException("File not found!");
		}

		return upload(new FileInputStream(file), ftpDirectoryPath, fileName);
	}

	/**
	 * Upload file to ftp server, the file name on ftp server same as localFile.
	 * @param localFile: Local file.
	 * @param ftpDirectoryPath: The ftp directory without after host address.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean upload(String localFile, String ftpDirectoryPath) throws IOException {
		File file = new File(localFile);

		if (!file.exists()) {
			throw new IOException("File not found!");
		}

		return upload(new FileInputStream(file), ftpDirectoryPath, file.getName());
	}

	/**
	 * Download a file from ftp server.
	 * @param ftpDirectoryPath: The ftp directory without after host address.
	 * @param fileName: File name used on ftp server.
	 * @param outputStream: OutputStream used for download.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean download(String ftpDirectoryPath, String fileName, OutputStream outputStream) throws IOException {
		if (!ftpDirectoryPath.startsWith("/")) {
			ftpDirectoryPath = "/" + ftpDirectoryPath;
		}

		if (!ftpClient.changeWorkingDirectory(ftpDirectoryPath)) {
			return false;
		}

		return ftpClient.retrieveFile(fileName, outputStream);
	}

	/**
	 * Download a file from ftp server.
	 * @param ftpDirectoryPath: The ftp directory without after host address.
	 * @param fileName: File name used on ftp server.
	 * @param localFile: Local file path.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean download(String ftpDirectoryPath, String fileName, String localFile) throws IOException {
		try (OutputStream outputStream = new FileOutputStream(localFile)) {
			return download(ftpDirectoryPath, fileName, outputStream);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Remove file on ftp server.
	 * @param ftpDirectoryPath: The ftp directory without after host address.
	 * @param fileName: File name used on ftp server.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean remove(String ftpDirectoryPath, String fileName) throws IOException {
		if (!ftpDirectoryPath.startsWith("/")) {
			ftpDirectoryPath = "/" + ftpDirectoryPath;
		}

		if (!ftpClient.changeWorkingDirectory(ftpDirectoryPath)) {
			throw new IOException("Change working direcroty failed!");
		}

		return ftpClient.deleteFile(fileName);
	}
}