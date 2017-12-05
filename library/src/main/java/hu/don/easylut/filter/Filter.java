package hu.don.easylut.filter;

import android.graphics.Bitmap;
import android.widget.ImageView;

public interface Filter {

    Bitmap apply(Bitmap src);

    void apply(ImageView imageView);

}
