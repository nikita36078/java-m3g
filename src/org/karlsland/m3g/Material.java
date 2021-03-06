package org.karlsland.m3g;

public class Material extends Object3D {

    public final static int AMBIENT  = 1024;
    public final static int DIFFUSE  = 2048;
    public final static int EMISSIVE = 4096;
    public final static int SPECULAR = 8192;

    native private void    jni_initialize                   ();
    native private void    jni_finalize                     ();
    native private int     jni_getColor                     (int     target);
    native private float   jni_getShininess                 ();
    native private boolean jni_isVertexColorTrackingEnabled ();
    native private void    jni_setColor                     (int     target, int ARGB);
    native private void    jni_setShininess                 (float   shininess);
    native private void    jni_setVertexColorTrackingEnable (boolean enable);
    native private String  jni_print                        ();

    public Material () {
    	jni_initialize ();
    }

    @Override
    protected void finalize () {
        jni_finalize ();
    }

    public int getColor (int target) {
        return jni_getColor (target);
    }

    public float getShininess () {
        return jni_getShininess ();
    }

    public boolean isVertexColorTrackingEnabled () {
        return jni_isVertexColorTrackingEnabled ();
    }

    public void setColor (int target, int ARGB) {
        jni_setColor (target, ARGB);
    }

    public void setShininess (float shininess) {
        jni_setShininess (shininess);
    }

    public void setVertexColorTrackingEnable (boolean enable) {
        jni_setVertexColorTrackingEnable (enable);
    }

    @Override
    public String toString () {
        return jni_print ();
    }

}