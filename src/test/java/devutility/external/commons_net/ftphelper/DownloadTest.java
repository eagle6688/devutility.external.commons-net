package devutility.external.commons_net.ftphelper;

import devutility.external.commons_net.BaseTest;
import devutility.external.commons_net.FtpHelper;
import devutility.internal.test.TestExecutor;

public class DownloadTest extends BaseTest {
	@Override
	public void run() {
		try (FtpHelper ftpHelper = super.ftpHelper) {
			ftpHelper.connect();

			boolean result = ftpHelper.download(ftpInstance.getPath("test/asd"), "asd.jpg", "E:\\Downloads\\asdCopy.jpg");
			println(String.valueOf(result));

			result = ftpHelper.download(ftpInstance.getPath("test/asd"), "test.png", "E:\\Downloads\\testCopy.png");
			println(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(DownloadTest.class);
	}
}