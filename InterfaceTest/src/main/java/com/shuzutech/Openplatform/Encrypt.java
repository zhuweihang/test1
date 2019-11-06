package com.shuzutech.Openplatform;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by zsx on 2017/1/14.
 */
public class Encrypt {

    /*
      Encrypt the plain text using public key.

      @param text
     *          : original plain text
     * @param key
     *          :The public key
     * @return Encrypted text
     * @throws java.lang.Exception
     */
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] rsaEncrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Decrypt text using private key.
     *
     * @param text :encrypted text
     * @param key  :The private key
     * @return plain text
     */
    public static String rsaDecrypt(byte[] text, PrivateKey key) {
        byte[] dectyptedText;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA");
            // decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);
            return new String(dectyptedText);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static byte[] rsaSign(String data, PrivateKey privateKey) {
        Signature signature;
        try {
            signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initSign(privateKey);
            signature.update(data.getBytes("UTF-8"));
            return signature.sign();

        } catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean rsaCheckSign(String data, byte[] signData, PublicKey publicKey) {

        if (data == null) {
            return false;
        }

        Signature signature;
        try {
            signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initVerify(publicKey);
            signature.update(data.getBytes("UTF-8"));
            return signature.verify(signData);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] base64Decode(String encode) {

        Base64 decoder = new Base64();
        byte[] b = decoder.decode(encode.getBytes());
        return b;
    }

    public static PublicKey StringtoPublicKey(String key) {
        byte[] publicBytes = Base64.decodeBase64(key.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory;
        PublicKey pubKey = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            pubKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return pubKey;
    }

    public static PrivateKey StringtoPrivateKey(String key) {
        byte[] publicBytes = Base64.decodeBase64(key.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(publicBytes);
        KeyFactory keyFactory;
        PrivateKey priKey = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            priKey = keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return priKey;
    }

    private static SecretKeySpec aesSetKey(String myKey) {
        byte[] key;
        SecretKeySpec secretKey;
        //MessageDigest sha;
        try {
            key = myKey.getBytes("UTF-8");
//            sha = MessageDigest.getInstance("SHA-1");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            return secretKey;
        }
//        catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密方法，调这个方法
     *
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String aesEncrypt(String strToEncrypt, String secret) {
        try {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return new String(Base64.encodeBase64(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
            //Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }


    /**
     * 对文件的内容进行加密时优先使用该方法
     *
     * @param bytes
     * @param secret
     * @return
     */
    public static byte[] aesEncryptBytes(byte[] bytes, String secret) {
        try {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return Base64.encodeBase64(cipher.doFinal(bytes));
            //Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * @param strToDecrypt
     * @param secret
     * @return
     * @deprecated
     */
    public static String aesDecrypt(byte[] strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] tmp = cipher.doFinal(strToDecrypt);
            return new String(tmp, "utf-8");
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static byte[] aesDecryptBytes(byte[] strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(strToDecrypt);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "jDgNJ30ZmPjAEkWE1q9A5uknrO7Ik8IeFh8gUkoXeG9TjCqCH3GBrvN6kOXnZHzRRFi/1aAWwSAOn8AbRtNplkjTX+QF/AkBThfY4Dp4OcVXZjYTOGyDOfC3QyJ0DMaryuXg1dW5Av7uoErNWniY9AEFJHIwzuIOEdbVHkBsH0N+XSl3894qclzHd7avebqOjmhIjtLFEICnax6w0busB31sCphBSGYTz2/EzcTCD8RP8w21Z6/cqYqDxE+/cMrJiyqzhp8Vp7vmeKAawKJ6TRl5dIAjWz6WLi0ZBh3fxANt2hctGSU5QK4/QGz6WUpbK+EVgCLSMx/cEPZzR5EtVA==";
        Base64 decoder = new Base64();
        System.out.println(s.length());
        byte[] encryptKey = decoder.decode(s.getBytes());
        System.out.println("success=" + encryptKey.length);
        String ss = "UA=Chrome&fpqqlsh=ZB01010101010194&kplx=1&fplxdm=026&shnsrsbh=110101201707010043&jsbh=110101201707010037~~A10016420000196&je=10&yfpdm=150003522222&yfphm=17727164";
        String AccessToken = "552d851dac0b0452825d6f21d535a744";
        String ecoderResult = null;
        try {
            ecoderResult = java.util.Base64.getEncoder().encodeToString(new Encrypt().aesEncrypt(ss, AccessToken).getBytes("utf-8"));
            System.out.println("加密后的数据为:" + ecoderResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String uri = "http://106.14.193.154:8081/invoice/h5/invoice?appId=00ca44d59d6c121bdd567053fac8dcda&encryptMsg=";
        String returnUri = "&returnURL=http://baidu.com";
        String url = uri + ecoderResult + returnUri;
        System.out.println(url);
    }
}
