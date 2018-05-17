package devutility.external.commons_net;

import java.lang.reflect.InvocationTargetException;

import devutility.internal.util.PropertiesUtils;

public class FtpUtils {
	public static FtpInstance getInstance(String propertiesFile, String prefix) throws NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return PropertiesUtils.toModel(propertiesFile, prefix, FtpInstance.class);
	}
}