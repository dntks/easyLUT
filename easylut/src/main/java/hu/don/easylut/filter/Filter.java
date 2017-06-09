package hu.don.easylut.filter;

import android.graphics.Bitmap;
import android.widget.ImageView;

public interface Filter {

    Bitmap applyFilterToBitmap(Bitmap src);

    void applyFilterToImageView(ImageView imageView);

}
