package hu.don.easylut.filter;


import android.graphics.Bitmap;

import hu.don.easylut.lutimage.LUTImage;

public class CreatingNewBitmap implements BitmapStrategy {
    @Override
    public Bitmap applyLut(Bitmap src, LUTImage lutImage) {
        int mWidth = src.getWidth();
        int mHeight = src.getHeight();
        int[] pix = new int[mWidth * mHeight];
        src.getPixels(pix, 0, mWidth, 0, 0, mWidth, mHeight);

        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                int index = y * mWidth + x;
                int pixel = pix[index];

                pix[index] = lutImage.getColorPixelOnLut(pixel);
            }
        }
        Bitmap bm = Bitmap.createBitmap(mWidth, mHeight, src.getConfig());
        bm.setPixels(pix, 0, mWidth, 0, 0, mWidth, mHeight);
        return bm;
    }
}
