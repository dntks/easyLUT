package hu.don.easylut.filter;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class FilterNon implements Filter {

    @Override
    public Bitmap apply(Bitmap source) {
        return source;
    }

    @Override
    public void apply(ImageView imageView) {
    }

}
