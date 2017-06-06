package hu.don.easylut.easylut;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class LUTFilter implements Filter {

    Resources resources;
    int LUTBitmapId, smallLutId;

    public LUTFilter(Resources resources, int lUTBitmapId, int smallLutId) {
        this.resources = resources;
        LUTBitmapId = lUTBitmapId;
        this.smallLutId = smallLutId;
    }

    @Override
    public Bitmap applySmallFilterToBitmap(Bitmap src) {
        Bitmap lutBitmap = BitmapFactory.decodeResource(resources, smallLutId);
        LUTImage lutImage = LUTImage.createLutImage(lutBitmap);
        return applyUniversal(src, lutImage, 0);
    }

    @Override
    public Bitmap applyFilterToBitmap(Bitmap src) {
        Bitmap lutBitmap = BitmapFactory.decodeResource(resources, LUTBitmapId);
        LUTImage lutImage = LUTImage.createLutImage(lutBitmap);
        return applyUniversal(src, lutImage, 8);
    }


    private Bitmap applyUniversal(Bitmap src, LUTImage lutImage,  int rowDepth) {
        int lutColors[] = lutImage.lutColors;

        int mWidth = src.getWidth();
        int mHeight = src.getHeight();
        int[] pix = new int[mWidth * mHeight];
        src.getPixels(pix, 0, mWidth, 0, 0, mWidth, mHeight);

        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                int index = y * mWidth + x;
                int pixel = pix[index];

                int lutIndex = getLutIndex(lutImage, rowDepth, pixel);
                pix[index] = getPixelOnLut(lutColors, lutIndex);
            }
        }
        Bitmap bm = Bitmap.createBitmap(mWidth, mHeight, src.getConfig());
        bm.setPixels(pix, 0, mWidth, 0, 0, mWidth, mHeight);
        pix = null;
        return bm;
    }

    private int getPixelOnLut(int[] lutColors, int lutIndex) {
        int R = ((lutColors[lutIndex] >> 16) & 0xff);
        int G = ((lutColors[lutIndex] >> 8) & 0xff);
        int B = ((lutColors[lutIndex]) & 0xff);
        return 0xff000000 | (R << 16) | (G << 8) | B;
    }

    private int getLutIndex(LUTImage lutImage, int rowDepth, int pixel) {
        int r = ((pixel >> 16) & 0xff) / lutImage.rgbDistortion;
        int g = ((pixel >> 8) & 0xff) / lutImage.rgbDistortion;
        int b = (pixel & 0xff) / lutImage.rgbDistortion;
        final int blueXDepth = rowDepth == 0 ? b : b % rowDepth;
        final int blueYDepth = rowDepth == 0 ? 0 : b / rowDepth;
        int lutX = blueXDepth * lutImage.sideSize + r;
        int lutY = blueYDepth * lutImage.sideSize + g;
        return lutY * lutImage.lutWidth + lutX;
    }

    @Override
    public void applyFilterToImageView(ImageView imageView) {
        BitmapDrawable imageDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap source = imageDrawable.getBitmap();
        Bitmap bitmap = applyFilterToBitmap(source);
        imageView.setImageBitmap(bitmap);

    }

    @Override
    public String getReadableName() {
        return resources.getResourceEntryName(LUTBitmapId);
    }

}
