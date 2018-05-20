/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <string>
#include "MD5.cpp"
#include "AESMain.cpp"

#ifndef _Included_com_yumi_encryptlibrary_encrypt_EncryptUtils
#define _Included_com_yumi_encryptlibrary_encrypt_EncryptUtils
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_yumi_encryptlibrary_encrypt_EncryptUtils
 * Method:    encryptAES
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */

JNIEXPORT jstring JNICALL Java_com_yumi_encryptlibrary_encrypt_EncryptUtils_encryptAES
        (JNIEnv *env, jclass obj, jstring input_) {
    const std::string new_string = env->GetStringUTFChars(input_, false);
    std::string aesStr = EncryptionAES(new_string);
    return env->NewStringUTF(aesStr.c_str());
}

JNIEXPORT jstring JNICALL Java_com_yumi_encryptlibrary_encrypt_EncryptUtils_decryptAES
        (JNIEnv *env, jclass obj, jstring input_) {
    const std::string new_string = env->GetStringUTFChars(input_, false);
    std::string aesStr = DecryptionAES(new_string);
    return env->NewStringUTF(aesStr.c_str());
}

/*
 * Class:     com_yumi_encryptlibrary_encrypt_EncryptUtils
 * Method:    encryptMD5
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */

const std::string MD5_Key = "492c6507922a4173a5e34a20bbb657b2";
JNIEXPORT jstring JNICALL Java_com_yumi_encryptlibrary_encrypt_EncryptUtils_encryptMD5
        (JNIEnv *env, jclass obj, jstring string1) {
    const std::string new_string = env->GetStringUTFChars(string1, false);
    std::string md5Str = md5(new_string+MD5_Key);
    return env->NewStringUTF(md5Str.c_str());
}


#ifdef __cplusplus
}
#endif
#endif
