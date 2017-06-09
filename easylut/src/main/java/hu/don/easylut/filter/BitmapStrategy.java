package hu.don.easylut.filter;


import android.graphics.Bitmap;

import hu.don.easylut.lutimage.LUTImage;

public interface BitmapStrategy {

    Bitmap applyLut(Bitmap src, LUTImage lutImage);

    enum Type{
        APPLY_ON_ORIGINAL_BITMAP, CREATING_NEW_BITMAP
    }
}
