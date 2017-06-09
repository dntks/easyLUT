package hu.don.easylut.filter;


import android.graphics.Bitmap;

public class LutFilterFromBitmap extends LUTFilter {

    private final Bitmap bitmap;

    private LutFilterFromBitmap(Bitmap bitmap, BitmapStrategy strategy) {
        super(strategy);
        this.bitmap = bitmap;
    }

    protected Bitmap getLUTBitmap() {
        return bitmap;
    }

    public static class Builder extends LUTFilter.Builder<Builder>{

        private Bitmap bitmap;

        public Builder withBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Filter createFilter() {
            return new LutFilterFromBitmap(bitmap, strategy);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
