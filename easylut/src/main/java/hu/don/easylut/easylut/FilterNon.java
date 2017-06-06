package hu.don.easylut.easylut;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class FilterNon implements Filter {

	@Override
	public Bitmap applyFilterToBitmap(Bitmap source) {
		return source;
	}

	@Override
	public Bitmap applySmallFilterToBitmap(Bitmap source) {
		return source;
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
		return "No filter";
	}

}
