package com.framework.cloud.common.utils;

import cn.hutool.core.codec.Base64;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA
 *
 * @author wusiwei
 */
public class RsaUtil {

    private static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String RSA_TYPE = "RSA/ECB/PKCS1Padding";

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param privateKey 私钥
     * @return 加密数据
     */
    public static String privateEncrypt(String data, String privateKey) {
        try {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(RSA_TYPE);
            //每个Cipher初始化方法使用一个模式参数opmod，并用此模式初始化Cipher对象。此外还有其他参数，包括密钥key、包含密钥的证书certificate、算法参数params和随机源random。
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            return Base64.encodeUrlSafe(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(StandardCharsets.UTF_8), rsaPrivateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     *
     * @param data 密文
     * @return 明文
     */
    public static String publicDecrypt(String data, String publicKeyName) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_TYPE);
            RSAPublicKey publicKey = (RSAPublicKey) getPublicKey(publicKeyName);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decode(data), publicKey.getModulus().bitLength()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }



    /**
     * 公钥加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String publicEncrypt(String data, String publicKeyName) {
        try {
            RSAPublicKey key = (RSAPublicKey) getPublicKey(publicKeyName);
            Cipher cipher = Cipher.getInstance(RSA_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encodeUrlSafe(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(StandardCharsets.UTF_8), key.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     *
     * @param data 密文
     * @return 明文
     */
    public static String privateDecrypt(String data, String privateKeyName) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_TYPE);

            RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(privateKeyName);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decode(data), privateKey.getModulus().bitLength()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA私钥 签名
     *
     * @param requestData    签名内容
     * @param privateKeyName 私钥
     * @return sign
     */
    public static String sign(String requestData, String privateKeyName) {
        String signature = null;
        byte[] signed = null;
        try {
            PrivateKey privateKey = getPrivateKey(privateKeyName);
            Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            sign.initSign(privateKey);
            sign.update(requestData.getBytes());
            signed = sign.sign();
            signature = Base64.encode(signed);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }


    /**
     * RSA公钥 验证签名
     *
     * @param requestData   签名内容
     * @param signature     base64签名
     * @param publicKeyName 公钥
     * @return bool
     */
    public static boolean verifySign(String requestData, String signature, String publicKeyName) {
        boolean verifySignSuccess = false;
        try {
            PublicKey publicKey = getPublicKey(publicKeyName);
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(requestData.getBytes());
            verifySignSuccess = verifySign.verify(Base64.decode(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return verifySignSuccess;
    }


    /**
     * 加密
     *
     * @param clearText     加密字符串
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String clearText, String publicKey) {
        String encryptedBase64 = "";
        try {
            Key key = getPublicKey(publicKey);
            final Cipher cipher = Cipher.getInstance(RSA_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes(StandardCharsets.UTF_8));
            encryptedBase64 = Base64.encode(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBase64;
    }

    /**
     * 解密
     *
     * @param encryptedBase64 加密后字符串
     * @param privateKey  私钥
     * @return 明文
     */
    public static String decrypt(String encryptedBase64, String privateKey) {
        String decryptedString = "";
        try {
            Key key = getPrivateKey(privateKey);
            final Cipher cipher = Cipher.getInstance(RSA_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.decode(encryptedBase64);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

    /**
     * 获取RSA公钥
     *
     * @param publicKey 公钥
     * @return 公钥
     */
    private static PublicKey getPublicKey(String publicKey) {
        try {
            byte[] byteKey = Base64.decode(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(x509EncodedKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取RSA私钥
     *
     * @param privateKey 私钥
     * @return 私钥
     */
    private static PrivateKey getPrivateKey(String privateKey) {
        try {
            byte[] byteKey = Base64.decode(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * rsa切割解码  , ENCRYPT_MODE,加密数据   ,DECRYPT_MODE,解密数据
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        //最大块
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    //可以调用以下的doFinal（）方法完成加密或解密数据：
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] result = out.toByteArray();
        IOUtils.closeQuietly(out);
        return result;
    }
}
