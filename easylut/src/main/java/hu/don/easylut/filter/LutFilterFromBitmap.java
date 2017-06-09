package hu.don.easylut.filter;


import android.graphics.Bitmap;

import hu.don.easylut.lutimage.CoordinateToColor;

public class LutFilterFromBitmap extends LUTFilter {

    private final Bitmap bitmap;

    private LutFilterFromBitmap(Bitmap bitmap, BitmapStrategy strategy, CoordinateToColor.Type coordinateToColorType) {
        super(strategy, coordinateToColorType);
        this.bitmap = bitmap;
    }

    protected Bitmap getLUTBitmap() {
        return bitmap;
    }

    public static class Builder extends LUTFilter.Builder<Builder> {

        private Bitmap bitmap;

        public Builder withBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Filter createFilter() {
            return new LutFilterFromBitmap(bitmap, strategy, coordinateToColorType);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
