#include <jni.h>
#include <iostream>
#include <sstream>
#include "java-m3g.hpp"
#include "java-m3g-common.hpp"
#include "m3g/m3g.hpp"
using namespace std;
using namespace m3g;

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_initialize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_karlsland_m3g_Material_jni_1initialize
  (JNIEnv* env, jobject thiz)
{
    //cout << "Java-Material: initilize is called.\n";
    Material* mat = NULL;
    __TRY__;
    mat = new Material ();
    __CATCH__;
    if (env->ExceptionOccurred ()) {
        return;
    }
    setNativePointer  (env, thiz, mat);
    bindJavaReference (env, thiz, mat);
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_finalize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_karlsland_m3g_Material_jni_1finalize
  (JNIEnv* env, jobject thiz)
{
    //cout << "Java-Material: finalize is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    releaseJavaReference (env, mat);
    addUsedObject (mat);
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_getColor
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_karlsland_m3g_Material_jni_1getColor
  (JNIEnv* env, jobject thiz, jint target)
{
    //cout << "Java-Material: getColor is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    int color = 0;
    __TRY__;
    color = mat->getColor (target);
    __CATCH__;
    return color;
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_getShininess
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_karlsland_m3g_Material_jni_1getShininess
  (JNIEnv* env, jobject thiz)
{
    //cout << "Java-Material: getShininess is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    float shine = 0;
    __TRY__;
    shine = mat->getShininess ();
    __CATCH__;
    return shine;
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_isVertexColorTrackingEnabled
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_karlsland_m3g_Material_jni_1isVertexColorTrackingEnabled
  (JNIEnv* env, jobject thiz)
{
    //cout << "Java-Material: isVertexColorTrackingEnabled is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    bool enabled = false;
    __TRY__;
    enabled = mat->isVertexColorTrackingEnabled ();
    __CATCH__;
    return enabled;
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_setColor
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_karlsland_m3g_Material_jni_1setColor
  (JNIEnv* env, jobject thiz, jint target, jint ARGB)
{
    //cout << "Java-Material: setColor is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    __TRY__;
    mat->setColor (target, ARGB);
    __CATCH__;
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_setShininess
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_karlsland_m3g_Material_jni_1setShininess
  (JNIEnv* env, jobject thiz, jfloat shininess)
{
    //cout << "Java-Material: setShininess is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    __TRY__;
    mat->setShininess (shininess);
    __CATCH__;
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_setVertexColorTrackingEnable
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_karlsland_m3g_Material_jni_1setVertexColorTrackingEnable
  (JNIEnv* env, jobject thiz, jboolean enable)
{
    //cout << "Java-Material: setVerteColorTrackingEnable is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    __TRY__;
    mat->setVertexColorTrackingEnable (enable);
    __CATCH__;
}

/*
 * Class:     org_karlsland_m3g_Material
 * Method:    jni_print
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_karlsland_m3g_Material_jni_1print
  (JNIEnv* env, jobject thiz)
{
    //cout << "Java-Material: print is called.\n";
    Material* mat = (Material*)getNativePointer (env, thiz);
    ostringstream oss;
    __TRY__;
    mat->print (oss);
    __CATCH__;
    return env->NewStringUTF (oss.str().c_str());
}

void Java_new_Material            (JNIEnv* env, m3g::Object3D* obj)
{
    //cout << "Java-Loader: build java Material.\n";
    Material* mat     = dynamic_cast<Material*>(obj);
    jobject   mat_obj = allocJavaObject (env, "org/karlsland/m3g/Material");
    setNativePointer  (env, mat_obj, mat);
    bindJavaReference (env, mat_obj, mat);

    Java_build_Object3D (env, mat_obj, mat);
    Java_build_Material (env, mat_obj, mat);

    env->DeleteLocalRef (mat_obj);
}

void Java_build_Material (JNIEnv* env, jobject mat_obj, m3g::Material* mat)
{
    // nothing to do
}
