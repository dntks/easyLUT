package hu.don.easylut.easylut;


import android.graphics.Bitmap;

public class LUTImage {
    private static final int COLOR_DEPTH = 256;
    public final int lutWidth;
    public final int lutHeight;
    public final int sideSize;
    public final int rgbDistortion;
    public final int lutColors[];

    public LUTImage(int lutWidth, int lutHeight, int[] lutColors) {
        this.lutWidth = lutWidth;
        this.lutHeight = lutHeight;
        this.lutColors = lutColors;
        this.sideSize = sideSize();
        this.rgbDistortion = COLOR_DEPTH / sideSize;
    }

    public static LUTImage createLutImage(Bitmap lutBitmap) {
        int lutWidth = lutBitmap.getWidth();
        int lutColors[] = new int[lutWidth * lutBitmap.getHeight()];
        lutBitmap.getPixels(lutColors, 0, lutWidth, 0, 0, lutWidth, lutBitmap.getHeight());
        LUTImage lutImage = new LUTImage(lutWidth, lutBitmap.getHeight(), lutColors);
        lutBitmap.recycle();
        return lutImage;
    }

    private int sideSize() {
        boolean isLutSquare = lutWidth == lutHeight;
        if (isLutSquare) {
            final double lutRoot = Math.pow(lutWidth * lutWidth, 1d / 3d);
            return (int) Math.round(lutRoot);
        }
        int smallerSide = lutWidth > lutHeight ? lutHeight : lutWidth;
        int longerSide = lutWidth > lutHeight ? lutWidth : lutHeight;

        double lutRoot = Math.pow(smallerSide * longerSide, 1d / 3d);
        return (int) Math.round(lutRoot);
    }

}
