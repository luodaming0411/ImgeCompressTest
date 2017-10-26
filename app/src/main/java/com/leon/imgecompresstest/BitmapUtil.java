package com.leon.imgecompresstest;

import android.graphics.Bitmap;

/**
 * User: Leon-Luo(leon041196@gmail.com)
 * Date: 2017-08-17
 * Time: 14:28
 * Intro:
 */
public class BitmapUtil {

    static {
        System.loadLibrary("jpeg");
        System.loadLibrary("compressimg");
    }

    public native static int compressBitmapByJni(Object bitmap, int quality, String fileName);
}
