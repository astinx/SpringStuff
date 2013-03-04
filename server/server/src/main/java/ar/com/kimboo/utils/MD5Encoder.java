package ar.com.kimboo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {

	public static String encode(String password) throws NoSuchAlgorithmException {
        String toEnc = password; // Value to encrypt
        MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
        mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted 
        return md5;
	}

}
