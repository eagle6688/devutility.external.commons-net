package devutility.external.commons_net.ftphelper;

import devutility.external.commons_net.BaseTest;
import devutility.external.commons_net.FtpHelper;
import devutility.internal.test.TestExecutor;

public class CreateDirecrotyTest extends BaseTest {
	@Override
	public void run() {
		try (FtpHelper ftpHelper = super.ftpHelper) {
			ftpHelper.connect();

			boolean result = ftpHelper.createDirecroty(ftpInstance.getPath("test/asd/fgh"));
			println(String.valueOf(result));

			result = ftpHelper.createDirecroty(ftpInstance.getPath("test/123/456"));
			println(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(CreateDirecrotyTest.class);
	}
}