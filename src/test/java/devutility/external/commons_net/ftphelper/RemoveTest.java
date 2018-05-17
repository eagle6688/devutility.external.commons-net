package devutility.external.commons_net.ftphelper;

import devutility.external.commons_net.BaseTest;
import devutility.external.commons_net.FtpHelper;
import devutility.internal.test.TestExecutor;

public class RemoveTest extends BaseTest {
	@Override
	public void run() {
		try (FtpHelper ftpHelper = super.ftpHelper) {
			ftpHelper.connect();
			boolean result = ftpHelper.remove(ftpInstance.getPath("test/asd"), "asd.jpg");
			println(String.valueOf(result));

			result = ftpHelper.remove(ftpInstance.getPath("test/asd"), "test.png");
			println(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(RemoveTest.class);
	}
}