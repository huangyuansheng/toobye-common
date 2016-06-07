/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/27.
 * 
 */
package com.toobye.common.codec;

import java.security.Key;
import java.security.SecureRandom;

import javax.annotation.Nonnull;
import javax.crypto.KeyGenerator;

import com.toobye.common.lang.Checks;

/**
 * <pre> 加解密算法.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/27  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Cipher {
	
	private javax.crypto.Cipher encrypt;
	private javax.crypto.Cipher decrypt;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param encrypt 加密
	 * @param decrypt 解密
	 */
	private Cipher(@Nonnull final javax.crypto.Cipher encrypt, @Nonnull final javax.crypto.Cipher decrypt) {
		Checks.nullThrow(encrypt);
		Checks.nullThrow(decrypt);
		this.encrypt = encrypt;
		this.decrypt = decrypt;
	}
	
	/**
	 * <pre> 加密.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @param content 待加密内容
	 * @return 密文
	 */
	@Nonnull
	public String encrypt(@Nonnull final String content) {
		Checks.emptyThrow(content);
		try {
			return HexCodec.encodeHexString(encrypt.doFinal(content.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 解密.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @param content 待解密内容
	 * @return 明文
	 */
	public String decrypt(final String content) {
		try {
			return new String((decrypt.doFinal(HexCodec.decodeHex(content))));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final String KEY_RANDOM_ALGORITHM = "SHA1PRNG";
	/**
	 * <pre> AES加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @param keyStr 密钥
	 * @param keySize 密钥长度
	 * @param keyAlgorithm 密钥算法
	 * @param cipherAlgorithm 加解密器算法
	 * @return 加解密器
	 */
	@Nonnull
	private static Cipher of(@Nonnull final String keyStr, @Nonnull final int keySize, @Nonnull final String keyAlgorithm, @Nonnull final String cipherAlgorithm) {
		Checks.emptyThrow(keyStr);
		Checks.emptyThrow(keyAlgorithm);
		Checks.emptyThrow(cipherAlgorithm);
		try {
			// Key
	        KeyGenerator keyGen = KeyGenerator.getInstance(keyAlgorithm);
	        SecureRandom secureRandom = SecureRandom.getInstance(KEY_RANDOM_ALGORITHM);
	        secureRandom.setSeed(keyStr.getBytes());
	        keyGen.init(keySize, secureRandom);
	        Key key = keyGen.generateKey();
	        // Encrypt
	        javax.crypto.Cipher en = javax.crypto.Cipher.getInstance(cipherAlgorithm);
			en.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
			// Decrypt
			javax.crypto.Cipher de = javax.crypto.Cipher.getInstance(cipherAlgorithm);
			de.init(javax.crypto.Cipher.DECRYPT_MODE, key);
			return new Cipher(en, de);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final String DEFAULT_KEY = "www.toobye.com";
	private static final String AES_ALGORITHM = "AES";
	private static final int AES_KEY_SIZE = 128;
	/**
	 * <pre> AES加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @param keyStr 密钥
	 * @return 加解密器
	 */
	@Nonnull
	public static Cipher ofAES(@Nonnull final String keyStr) {
		return of(keyStr, AES_KEY_SIZE, AES_ALGORITHM, AES_ALGORITHM);
	}
	/**
	 * <pre> AES加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @return 加解密器
	 */
	@Nonnull
	public static Cipher ofAES() {
		return ofAES(DEFAULT_KEY);
	}
	
	private static final String DES_KEY_ALGORITHM = "DES";
	private static final String DES_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	private static final int DES_KEY_SIZE = 56;
	/**
	 * <pre> DES加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @param keyStr 密钥
	 * @return 加解密器
	 */
	@Nonnull
	public static Cipher ofDES(@Nonnull final String keyStr) {
		return of(keyStr, DES_KEY_SIZE, DES_KEY_ALGORITHM, DES_CIPHER_ALGORITHM);
	}
	/**
	 * <pre> DES加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @return 加解密器
	 */
	@Nonnull
	public static Cipher ofDES() {
		return ofDES(DEFAULT_KEY);
	}
	
	private static final String DESEDE_KEY_ALGORITHM = "DESede";
	private static final String DESEDE_CIPHER_ALGORITHM = "DESede";
	private static final int DESEDE_KEY_SIZE = 168;
	/**
	 * <pre> DESede加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @param keyStr 密钥
	 * @return 加解密器
	 */
	@Nonnull
	public static Cipher ofDESede(@Nonnull final String keyStr) {
		return of(keyStr, DESEDE_KEY_SIZE, DESEDE_KEY_ALGORITHM, DESEDE_CIPHER_ALGORITHM);
	}
	/**
	 * <pre> DESede加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @return 加解密器
	 */
	@Nonnull
	public static Cipher ofDESede() {
		return ofDESede(DEFAULT_KEY);
	}
	
	/**
	 * <pre> 系统默认加解密器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/27  huangys  Create
	 * </pre>
	 * 
	 * @return 加解密器
	 */
	@Nonnull
	public static Cipher ofSystem() {
		return ofDES();
	}
	
}
