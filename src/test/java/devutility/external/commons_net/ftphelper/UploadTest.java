package devutility.external.commons_net.ftphelper;

import devutility.external.commons_net.BaseTest;
import devutility.external.commons_net.FtpHelper;
import devutility.internal.test.TestExecutor;

public class UploadTest extends BaseTest {
	@Override
	public void run() {
		try (FtpHelper ftpHelper = super.ftpHelper) {
			ftpHelper.connect();
			boolean result = ftpHelper.upload("E:\\Downloads\\asd.jpg", ftpInstance.getPath("test/asd"));
			println(String.valueOf(result));

			result = ftpHelper.upload("E:\\Downloads\\test.png", ftpInstance.getPath("test/asd"));
			println(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(UploadTest.class);
	}
}