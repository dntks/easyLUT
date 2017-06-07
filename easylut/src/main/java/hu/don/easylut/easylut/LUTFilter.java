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
        return applyUniversal(src, lutImage);
    }

    @Override
    public Bitmap applyFilterToBitmap(Bitmap src) {
        Bitmap lutBitmap = BitmapFactory.decodeResource(resources, LUTBitmapId);
        LUTImage lutImage = LUTImage.createLutImage(lutBitmap);
        return applyUniversal(src, lutImage);
    }


    private Bitmap applyUniversal(Bitmap src, LUTImage lutImage) {
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
        pix = null;
        return bm;
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
