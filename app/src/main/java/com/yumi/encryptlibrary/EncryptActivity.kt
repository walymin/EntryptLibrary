package com.yumi.encryptlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yumi.encryptlibrary.encrypt.EncryptUtils
import com.yumi.encryptlibrary.utils.UtiEncrypt
import kotlinx.android.synthetic.main.activity_encrypt.*

class EncryptActivity : AppCompatActivity() {
    val name: String = "wanglimin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypt)
        md5_cpp_text.text="md5_cpp_: "+ EncryptUtils.encryptMD5(name)
        md5_java_text.text = "md5_java_: "+ UtiEncrypt.encryptMD5(name)

        var aesCppEncrypt = EncryptUtils.encryptAES(name)
        aes_cpp_encrypt_text.text="aes_cpp_encrypt_: "+ aesCppEncrypt;

        var aesJavaEncrypt = UtiEncrypt.encryptAES(name);
        aes_java_encrypt_text.text="aes_java_encrypt_: "+aesJavaEncrypt

        aes_cpp_decrypt_text.text="aes_cpp_decrypt_: "+EncryptUtils.decryptAES(aesCppEncrypt)
        aes_java_decrypt_text.text="aes_java_decrypt_: "+UtiEncrypt.decryptAES(aesJavaEncrypt)
    }
}
