//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yumi.encryptlibrary.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class UtiEncrypt {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final byte[] bys = new byte[]{(byte)121, (byte)103, (byte)49, (byte)48, (byte)120, (byte)109, (byte)119, (byte)108, (byte)101, (byte)56, (byte)54, (byte)35, (byte)102, (byte)107, (byte)98, (byte)99};
    private static final String aa = "879FC8CB80958B9599C1CADF9F979B9A8190CBCC8E9F8B9198C0CADE9F939E9B8F";
    private static final String bb = "879BCDCC84918B9099C4CADF9A979E9F859AC7C98F978C9098C7C8D99E9D959F84";
    private static final String cc = "879BCDCC81918B9099C4CADF9F979E9F859ECDC984918E9099C1CFDA9A979E9A8599CCC882948A9198C7CBDE9D979899879BC8CE83938A9192C0C9D99F979F9585";
    private static final String md5 = "492c6507922a4173a5e34a20bbb657b2";
    private static final String aes = "ypXwd3#c85p643vM";
    public UtiEncrypt() {
    }

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);

        for(int i = 0; i < b.length; ++i) {
            sb.append(HEX_DIGITS[(b[i] & 240) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 15]);
        }

        return sb.toString();
    }

    public static String encryptMD5(String input) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update((input + md5).getBytes("utf-8"));
//            e.update((input + decrypt("879BCDCC81918B9099C4CADF9F979E9F859ECDC984918E9099C1CFDA9A979E9A8599CCC882948A9198C7CBDE9D979899879BC8CE83938A9192C0C9D99F979F9585", new String(bys))).getBytes("utf-8"));
            byte[] messageDigest = e.digest();
            return toHexString(messageDigest).toLowerCase();
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return "";
    }

    public static String encryptAES(String input) throws Exception {
        if(input != null && !input.equals("")) {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
//            SecretKeySpec keySpec = new SecretKeySpec(decrypt("879FC8CB80958B9599C1CADF9F979B9A8190CBCC8E9F8B9198C0CADE9F939E9B8F", new String(bys)).getBytes("utf-8"), "AES");
            SecretKeySpec keySpec = new SecretKeySpec(aes.getBytes("utf-8"), "AES");
            Log.d("admin-entrypt","--bys2string: "+new String(bys));
            Log.d("admin-entrypt","--iv2string: "+decrypt("879BCDCC84918B9099C4CADF9A979E9F859AC7C98F978C9098C7C8D99E9D959F84", new String(bys)));
            IvParameterSpec ivSpec = new IvParameterSpec(decrypt("879BCDCC84918B9099C4CADF9A979E9F859AC7C98F978C9098C7C8D99E9D959F84", new String(bys)).getBytes("utf-8"));
            cipher.init(1, keySpec, ivSpec);
            byte[] result = cipher.doFinal(input.getBytes("utf-8"));
            return Base64.encode(result);
        } else {
            return "";
        }
    }

    public static String decryptAES(String input) throws Exception {
        if(input != null && !input.equals("")) {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
//            SecretKeySpec keySpec = new SecretKeySpec(decrypt("879FC8CB80958B9599C1CADF9F979B9A8190CBCC8E9F8B9198C0CADE9F939E9B8F", new String(bys)).getBytes("utf-8"), "AES");
            SecretKeySpec keySpec = new SecretKeySpec(aes.getBytes("utf-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(decrypt("879BCDCC84918B9099C4CADF9A979E9F859AC7C98F978C9098C7C8D99E9D959F84", new String(bys)).getBytes("utf-8"));
            cipher.init(2, keySpec, ivSpec);
            byte[] result = cipher.doFinal(Base64.decode(input));
            return new String(result, "utf-8");
        } else {
            return "";
        }
    }

    public static String decrypt(String input, String key) throws Exception {
        byte[] c = fromHexString(input);
        byte[] k = key.getBytes("utf-8");
        byte[] p = new byte[c.length];

        int w;
        for(int t = 0; t < c.length; ++t) {
            w = t % k.length;
            p[t] = (byte)(~(c[t] ^ k[w]));
        }

        byte[] var8 = (byte[])null;
        if(p[0] == 0) {
            var8 = new byte[(p.length - 1) * 2];

            for(w = 1; w < p.length; ++w) {
                var8[(w - 1) * 2] = (byte)((p[w] & 240) >> 4);
                var8[w * 2 - 1] = (byte)(p[w] & 15);
            }
        } else {
            var8 = new byte[p.length - 1];

            for(w = 1; w < p.length; ++w) {
                var8[w - 1] = p[w];
            }
        }

        byte[] var9 = new byte[var8.length / 2];

        for(int i = 0; i < var9.length; ++i) {
            var9[i] = (byte)((var8[i] << 4) + var8[var8.length - i - 1]);
        }

        return new String(var9, "utf-8");
    }

    public static byte[] fromHexString(String s) {
        if(s.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[s.length() / 2];

            for(int i = 0; i < s.length() / 2; ++i) {
                int high = Integer.parseInt(s.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(s.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)((high << 4) + low);
            }

            return result;
        }
    }
}
