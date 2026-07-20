package com.userbo;

import java.net.URLDecoder;

public class LawyersValidationUtilsDecryptTest {
	public static void main(String[] args) throws Exception {
		LawyersValidationUtils utils = new LawyersValidationUtils();
		if (!"107394".equals(utils.decrypt("Z/Kc FgR41Q=")))
			throw new AssertionError("URL-decoded Base64 token was not decrypted");

		try {
			utils.decrypt("plain-text");
			throw new AssertionError("Invalid encrypted value was accepted");
		} catch (IllegalArgumentException expected) {
			// Expected.
		}

		String encryptedForUrl = utils.encryptForUrl("producer+code");
		String decoded = URLDecoder.decode(encryptedForUrl, "UTF-8");
		if (!"producer+code".equals(utils.decrypt(decoded)))
			throw new AssertionError("URL-safe encrypted value did not round-trip");
	}
}
