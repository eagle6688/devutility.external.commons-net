package devutility.external.commons_net.ftphelper;

import java.io.IOException;

import devutility.external.commons_net.BaseTest;
import devutility.internal.test.TestExecutor;

public class IsFileExistTest extends BaseTest {
	@Override
	public void run() {
		try {
			boolean result = ftpHelper.isFileExist("/commercial/2018/1/09/8383ee7a-e460-4f6a-b97b-5aa56a9bfc9a.png");
			println(String.valueOf(result));

			result = ftpHelper.isFileExist("/commercial/2018/1/09/test.png");
			println(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(IsFileExistTest.class);
	}
}