package cn.betatown.itplatform.common.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
	
	private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

	public static String UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static void main(String[] args) {
		
		logger.info("test log {} end",1);
		
		System.out.println("test".indexOf("test"));
		System.out.println("test".indexOf("cache"));
		
	}

}
