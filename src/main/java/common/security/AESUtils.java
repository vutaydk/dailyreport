package common.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import common.util.SystemConfig;

public class AESUtils {

	private static String key = "abcdefghijklmopa";
	private static SecretKeySpec keySpec;
	private static IvParameterSpec ivSpec;

	static {
		int ivSize = 16;
		byte[] iv = new byte[ivSize];
		SecureRandom ran = new SecureRandom();
		ran.nextBytes(iv);
		ivSpec = new IvParameterSpec(iv);

		// hash key
		String hashedKey = HashingUtil.hashBySHA256(key);
		int keySize = 16;
		byte[] keyByte = new byte[keySize];
		System.arraycopy(Base64.decodeBase64(hashedKey), 0, keyByte, 0, keySize);
		keySpec = new SecretKeySpec(key.getBytes(SystemConfig.DEFAULT_CHARSET), "AES");
	}

	private AESUtils() {

	}

	public static String encrypt(String input) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		byte[] textByte = input.getBytes(SystemConfig.DEFAULT_CHARSET);
		byte[] encryptedByte = cipher.doFinal(textByte);

		return Base64.encodeBase64String(encryptedByte);
	}

	public static String decrypt(String input) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		byte[] textByte = Base64.decodeBase64(input);
		byte[] decryptedByte = cipher.doFinal(textByte);

		return new String(decryptedByte, SystemConfig.DEFAULT_CHARSET);
	}
}
