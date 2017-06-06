package hu.don.easylut.easylut;

import android.graphics.Bitmap;
import android.widget.ImageView;

public interface Filter {
	Bitmap applyFilterToBitmap(Bitmap src);

	Bitmap applySmallFilterToBitmap(Bitmap src);

	void applyFilterToImageView(ImageView imageView);

	String getReadableName();
}
