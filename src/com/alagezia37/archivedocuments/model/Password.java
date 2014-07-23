package com.alagezia37.archivedocuments.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {
	public static final int SALT_BYTE_SIZE = 8;
	
	public static final int SALT_INDEX = 0;
	public static final int HASH_INDEX = 1;
	
	/**
	 * Generates a random salt	
	 */
	public static byte[] getSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		sr.nextBytes(salt);
		return salt;
	}
	
	/**
	 * Generates a salted SHA256 hash of the password
	 * @return					a salt and the salted SHA256 hash of the password 
	 * 							in hexadecimal form
	 */
	public static String getHash(String password) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		byte[] salt = getSalt();
		digest.update(salt);
		byte[] hash = digest.digest(password.getBytes("UTF-8"));
		return toHex(salt) + ":" + toHex(hash);
	}
	
	/**
	 * Validates a password using a hash.
	 * @param 	password		the password to check
	 * @param	correctHash		the hash of the valid password
	 * @return					true if the password is correct, false if not
	 */
	public static boolean validatePassword(String password, String correctHash) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// Decode the hash into its parameters
		String[] params = correctHash.split(":");
		byte[] salt = fromHex(params[SALT_INDEX]);
		byte[] hash = fromHex(params[HASH_INDEX]);
		
		// Compute the hash of the provided password
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		byte[] gotHash = digest.digest(password.getBytes("UTF-8"));
		
		// Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, gotHash);
	}
	
    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line 
     * system using a timing attack and then attacked off-line.
     * 
     * @param   	a       	the first byte array
     * @param   	b       	the second byte array 
     * @return          		true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return (diff == 0);
    }
	
	/**
	 * Converts a string of hexadecimal values into a byte array.
	 * @param		hex			a string of hexadecimal values that need to be converted
	 * @return					a converted from hex byte array				
	 */
	public static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte)Integer.parseInt(hex.substring(2 * i,  2 * (i + 1)), 16);
		}
		return binary;
	}
	
	/**
	 * Converts a byte array to a string of hexadecimal values
	 * @param 		binary		a byte array that need to be converted
	 * @return					a converted from binary string of hexadecimal values
	 */
	public static String toHex(byte[] binary) {
		BigInteger bi = new BigInteger(1, binary);
		String hex = bi.toString(16);
		int paddingLength = (binary.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}
	
	public static void main(String[] args) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
	}
}