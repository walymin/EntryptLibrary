#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
//这是个是新建c++项目自带的测试代码
JNICALL
Java_com_yumi_encryptlibrary_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++ click me ";
    return env->NewStringUTF(hello.c_str());
}
