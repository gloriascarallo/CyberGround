package control;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Security {

	public static String hmacSHA256(String data, String secret) {
	    try {
	        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	        Mac mac = Mac.getInstance("HmacSHA256");
	        mac.init(keySpec);
	        byte[] rawHmac = mac.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(rawHmac);
	    } catch (Exception e) {
	        throw new RuntimeException("Errore calcolo HMAC", e);
	    }
	}
	
	public static String toHash(String password) {
		String hashString = null;
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for (int i = 0; i < hash.length; i++) {
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1, 3);
			}
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return hashString;
	}
	
	
}
