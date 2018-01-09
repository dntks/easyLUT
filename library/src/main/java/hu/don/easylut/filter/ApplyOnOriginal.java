package hu.don.easylut.filter;

import android.graphics.Bitmap;

import hu.don.easylut.lutimage.LUTImage;

public class ApplyOnOriginal implements BitmapStrategy {

    @Override
    public Bitmap applyLut(Bitmap src, LUTImage lutImage) {
        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                int pixel = src.getPixel(x, y);

                int colorPixelOnLut = lutImage.getColorPixelOnLut(pixel);
                src.setPixel(x, y, colorPixelOnLut);
            }
        }
        return src;

    }
}
