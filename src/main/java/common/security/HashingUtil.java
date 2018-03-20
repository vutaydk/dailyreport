package common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import common.util.SystemConfig;

public class HashingUtil {

	private static MessageDigest digest;
	static {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * using SHA256 to hash input text
	 * 
	 * @param input
	 *            input string
	 * @return return string contain 44 character length
	 */
	public static String hashBySHA256(String input) {

		byte[] byteData = input.getBytes(SystemConfig.DEFAULT_CHARSET);
		byte[] hashedByte = digest.digest(byteData);

		return Base64.encodeBase64String(hashedByte);
	}
}
