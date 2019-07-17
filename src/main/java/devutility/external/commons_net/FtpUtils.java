package devutility.external.commons_net;

import java.util.Properties;

import devutility.internal.util.PropertiesUtils;

/**
 * 
 * FtpUtils
 * 
 * @author: Aldwin Su
 * @version: 2019-07-17 19:40:06
 */
public class FtpUtils {
	/**
	 * Return an FtpClientProperties object use value from specified properties file.
	 * @param propertiesFile Properties file name.
	 * @param prefix Key prefix in properties file.
	 * @return FtpClientProperties
	 * @throws Exception from toModel method.
	 */
	public static FtpClientProperties getInstance(String propertiesFile, String prefix) throws Exception {
		return PropertiesUtils.toModel(propertiesFile, prefix, FtpClientProperties.class);
	}

	/**
	 * Return an FtpClientProperties object use value from specified Properties object.
	 * @param properties Properties object.
	 * @param prefix Key prefix in properties file.
	 * @return FtpClientProperties
	 * @throws Exception from toModel method.
	 */
	public static FtpClientProperties getInstence(Properties properties, String prefix) throws Exception {
		return PropertiesUtils.toModel(properties, prefix, FtpClientProperties.class);
	}
}