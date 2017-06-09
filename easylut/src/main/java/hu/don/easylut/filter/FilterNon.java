package hu.don.easylut.filter;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class FilterNon implements Filter {

    @Override
    public Bitmap applyFilterToBitmap(Bitmap source) {
        return source;
    }

    @Override
    public void applyFilterToImageView(ImageView imageView) {
    }

}
