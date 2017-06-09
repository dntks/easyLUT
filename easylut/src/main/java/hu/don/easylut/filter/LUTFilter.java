package hu.don.easylut.filter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import hu.don.easylut.LUTImage;

public abstract class LUTFilter implements Filter {

    private final BitmapStrategy strategy;

    protected LUTFilter(BitmapStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Bitmap applyFilterToBitmap(Bitmap src) {
        Bitmap lutBitmap = getLUTBitmap();
        LUTImage lutImage = LUTImage.createLutImage(lutBitmap);
        return strategy.applyLut(src, lutImage);
    }

    protected abstract Bitmap getLUTBitmap();

    @Override
    public void applyFilterToImageView(ImageView imageView) {
        BitmapDrawable imageDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap source = imageDrawable.getBitmap();
        Bitmap bitmap = applyFilterToBitmap(source);
        imageView.setImageBitmap(bitmap);
    }

    public static abstract class Builder<B> {
        protected BitmapStrategy strategy = new CreatingNewBitmap();

        public B withStrategy(BitmapStrategy.Type strategy) {
            switch (strategy){
                case APPLY_ON_ORIGINAL_BITMAP:
                    this.strategy = new ApplyOnOriginal();
                    break;
                case CREATING_NEW_BITMAP:
                    this.strategy = new CreatingNewBitmap();
                    break;
            }
            return self();
        }

        protected abstract B self();
    }
}
