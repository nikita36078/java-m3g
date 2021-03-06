package org.karlsland.m3g;

public class Image2D extends Object3D {

    public final static int ALPHA           = 96;
    public final static int LUMINANCE       = 97;
    public final static int LUMINANCE_ALPHA = 98;
    public final static int RGB             = 99;
    public final static int RGBA            = 100;

    native private void    jni_initialize (int format, int width, int height);
    native private void    jni_initialize (int format, int width, int height, byte[] iamge);
    native private void    jni_initialize (int format, int width, int height, byte[] iamge, byte[] palette);
    native private void    jni_initialize (int format, java.lang.Object image);
    native private void    jni_finalize   ();
    native private int     jni_getFormat  ();
    native private int     jni_getHeight  ();
    native private int     jni_getWidth   ();
    native private boolean jni_isMutable  ();
    native private void    jni_set        (int x, int y, int width, int height, byte[] image);
    native private String  jni_print      ();
    native private void    jni_writePng   (java.lang.String name);

    public Image2D (int format, int width, int height) {
       jni_initialize(format, width, height);    		
    }
    
    public Image2D (int format, int width, int height, byte[] image) {
       jni_initialize(format, width, height, image);
    }

    public Image2D (int format, int width, int height, byte[] image, byte[] palette) {
        jni_initialize(format, width, height, image, palette);
     }

    public Image2D (int format, java.lang.Object image) {
    	jni_initialize(format, image);
    }

    @Override
    protected void finalize () {
        jni_finalize ();
    }

    public int getFormat () {
        return jni_getFormat ();
    }

    public int getHeight () {
        return jni_getHeight ();
    }

    public int getWidth () {
        return jni_getWidth ();
    }

    public boolean isMutable () {
        return jni_isMutable ();
    }

    public void set (int x, int y, int width, int height, byte[] image) {
        jni_set (x, y, width, height, image);
    }

    @Override
    public String toString () {
        return jni_print ();
    }

    public void writePng (java.lang.String name) {
        jni_writePng (name);
    }

}