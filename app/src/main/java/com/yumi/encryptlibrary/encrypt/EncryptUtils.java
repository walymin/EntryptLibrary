package com.yumi.encryptlibrary.encrypt;

/**
 * Description:
 * Created by wanglimin on 2017/12/5.
 * Version :1.0.0
 */

public class EncryptUtils {
    static {
        System.loadLibrary("encryptUtils");
    }

    public static native String encryptAES(String input);
    public static native String encryptMD5(String input);
    public static native String decryptAES(String input);
}
