package com.yumi.encryptlibrary.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * Created by wanglimin on 2017/12/26.
 * Version :1.0.0
 */

public class DeviceUtils {

    /**
     *没什么用
     * @return
     */
    public static String getBuildShortMakerLength() {
        String mMakerLength = "35";
        mMakerLength += Build.BOARD.length() % 10 + Build.BRAND.length() % 10
                + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
                + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
                + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
                + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
                + Build.TAGS.length() % 10 + Build.TYPE.length() % 10
                + Build.USER.length() % 10 + "";
        return mMakerLength;
    }

    /**
     * 通过两台类型一样的测试机器验证。打印出的一值一样，
     *
     *     W/deviceidd:  msm8916
                         qcom
                         armeabi-v7a
                         msm8916_32
                         SQ27T_P3_00AX_YBXX_AU1616_413_R_0_170207_01
                         M5
                         SQ27T_P3_00AX_YBXX_AU1616_413_R_0_170207_01
                         UBX
                         i9000S
                         i9000S
                         dev-keys
                         user
                         u
     * @return
     */
    public static String printbulidMaker() {
        String mstring = "";
        mstring += Build.BOARD +"\n"+ Build.BRAND+"\n"
                + Build.CPU_ABI +"\n"+ Build.DEVICE+"\n"
                + Build.DISPLAY +"\n"+ Build.HOST+"\n"
                + Build.ID +"\n"+ Build.MANUFACTURER+"\n"
                + Build.MODEL +"\n"+ Build.PRODUCT+"\n"
                + Build.TAGS +"\n"+ Build.TYPE+"\n"
                + Build.USER;
        return mstring;
    }

    /**
     * 获取WiFi mac地址
     * 需要权限 <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     * @param context
     * @return
     */
    public static String getWifiMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        if(!TextUtils.isEmpty(wifiMac)) {
            return wifiMac;
        }
        return "";
    }

    /**
     * 需要获取 android.Manifest.permission.READ_PHONE_STATE 权限
     * @param context
     * @return
     */
    public static String getSimSerialNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    /**
     * 通过两台相同的机器验证不一样
     * 设备1：be24812
     * 设备2：be168c4
     * @return
     */
    public static String getSerialNubmer() {
        return android.os.Build.SERIAL;
    }

    /**
     * 需要Manifest.permission.READ_PHONE_STATE权限
     * 在api 26下面的机器上是找不到这个方法，报错
     * java.lang.NoSuchMethodError: No static method getSerial()Ljava/lang/String; in class Landroid/os/Build;
     * or its super classes (declaration of 'android.os.Build' appears in /system/framework/framework.jar:classes2.dex)
     * @return
     */
    public static String getSerial() {
        return android.os.Build.getSerial();
    }

    /**
     * 需要获取 Manifest.permission.BLUETOOTH 权限
     * @return
     */
    public static String getBluetoothMacAddress() {
        return BluetoothAdapter.getDefaultAdapter().getAddress();
    }


    /**
     * 获取手机IMEI号
     * 需要动态权限: android.permission.READ_PHONE_STATE
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    /**
     * 获取android id
     * @param context
     * @return
     */
    public static String getAndroidID(Context context) {
//        Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static String getInstalltionID(Context context) {
        return Installation.id(context);
    }


    /**
     * 获取设备唯一标识
     * @param context
     * @return
     */
    public static String getUniqueId(Context context){
        String id = getAndroidID(context) + Build.SERIAL;
        try {
            return toMD5(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return id;
        }
    }



    private static String toMD5(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString();
    }
}
