/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobakodinga;

import sun.misc.*;
import javax.crypto.*;
import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author acer
 */
public abstract class enkripsiAES extends javax.swing.JFrame {
static byte[] ciphertext, plaintext, ivBytes;
    public static String encrypt(SecretKeySpec key, String plaintext) throws Exception {
           byte[] pesan = plaintext.getBytes();
           Cipher cipher = Cipher.getInstance("AES");
           cipher.init(Cipher.ENCRYPT_MODE, key);
           ciphertext = cipher.doFinal(pesan);
           String sandi = new BASE64Encoder().encode(ciphertext);
           return sandi;
    }
    public static String decrypt(SecretKeySpec key, String ciphertext) throws Exception {
           byte[] sandi = ciphertext.getBytes();
           Cipher cipher = Cipher.getInstance("AES");
           byte[] decodeCipher = new BASE64Decoder().decodeBuffer(ciphertext);
           cipher.init(Cipher.DECRYPT_MODE, key);
           plaintext = cipher.doFinal(decodeCipher);
           String pesan = new String(plaintext);
           return pesan;
    }
    public static SecretKeySpec genKey(String key) throws Exception {
           byte[] input = key.getBytes();
           SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
           KeyGenerator kgen = KeyGenerator.getInstance("AES");
           byte[] byteseed = new byte[256];
           secureRandom.setSeed(input);
           kgen.init(256, secureRandom);
           SecretKey kunci = kgen.generateKey();
           byte[] bytes = kunci.getEncoded();
           SecretKeySpec spec = new SecretKeySpec(bytes,"AES");
           return spec;
    }
}