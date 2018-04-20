package cn.betatown.itplatform.common.utils;

import java.util.UUID;

public class StringUtils {

	public static String UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}
}
