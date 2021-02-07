#include "HelloJNI.h"

JNIEXPORT void JNICALL Java_HelloJNI_sayHello(JNIEnv* env, jobject obj) {
    printf("Hello\n");
}