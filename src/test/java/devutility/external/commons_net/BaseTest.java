package devutility.external.commons_net;

import java.lang.reflect.InvocationTargetException;

public abstract class BaseTest extends devutility.internal.test.BaseTest {
	protected FtpHelper ftpHelper = null;
	protected FtpInstance ftpInstance = null;

	public BaseTest() {
		try {
			ftpInstance = FtpUtils.getInstance("system.properties", "ftp");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		ftpHelper = new FtpHelper(ftpInstance.getHost(), ftpInstance.getPort(), ftpInstance.getUserName(), ftpInstance.getPassword(), ftpInstance.getStart());
	}
}