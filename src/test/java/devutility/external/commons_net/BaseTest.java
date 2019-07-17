package devutility.external.commons_net;

public abstract class BaseTest extends devutility.internal.test.BaseTest {
	protected FtpHelper ftpHelper = null;
	protected FtpClientProperties ftpInstance = null;

	public BaseTest() {
		try {
			ftpInstance = FtpUtils.getInstance("system.properties", "ftp");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ftpHelper = new FtpHelper(ftpInstance.getHost(), ftpInstance.getPort(), ftpInstance.getUserName(), ftpInstance.getPassword(), ftpInstance.getStart());
	}
}