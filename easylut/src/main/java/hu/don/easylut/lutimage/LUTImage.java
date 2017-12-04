package hu.don.easylut.lutimage;


import android.graphics.Bitmap;
import android.graphics.Point;

public class LUTImage {
    private static final int COLOR_DEPTH = 256;
    public final int lutWidth;
    public final int lutHeight;
    public final int sideSize;
    public final int rgbDistortion;
    public CoordinateToColor coordinateToColor;
    public final int lutColors[];

    public LUTImage(int lutWidth, int lutHeight, int[] lutColors) {
        this.lutWidth = lutWidth;
        this.lutHeight = lutHeight;
        this.lutColors = lutColors;
        this.sideSize = sideSize();
        this.rgbDistortion = COLOR_DEPTH / sideSize;
    }

    public static LUTImage createLutImage(Bitmap lutBitmap,
                                          CoordinateToColor.Type coordinateToColorType) {
        int lutWidth = lutBitmap.getWidth();
        int lutColors[] = new int[lutWidth * lutBitmap.getHeight()];
        lutBitmap.getPixels(lutColors, 0, lutWidth, 0, 0, lutWidth, lutBitmap.getHeight());
        LUTImage lutImage;
        lutImage = new LUTImage(lutWidth, lutBitmap.getHeight(), lutColors);
        lutImage.coordinateToColor = coordinateToColorType.getCoordinateToColor(lutImage);

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

    public int rowDepth() {
        return lutHeight / sideSize;
    }

    public int columnDepth() {
        return lutWidth / sideSize;
    }

    public int getColorPixelOnLut(int pixelColor) {
        int lutIndex = getLutPixelIndex(pixelColor);
        return getPixelByIndex(lutIndex);
    }

    int getPixelByIndex(int lutIndex) {
        int red = ((lutColors[lutIndex] >> 16) & 0xff);
        int green = ((lutColors[lutIndex] >> 8) & 0xff);
        int blue = ((lutColors[lutIndex]) & 0xff);
        return 0xff000000 | (red << 16) | (green << 8) | blue;
    }

    private int getLutPixelIndex(int pixelColor) {
        Point point = getPointCoordinateOnLutImage(
                DistortedColor.getColorOnXCoordinate(this, pixelColor),
                DistortedColor.getColorOnYCoordinate(this, pixelColor),
                DistortedColor.getColorOnZCoordinate(this, pixelColor));
        return point.y * lutWidth + point.x;
    }

    private Point getPointCoordinateOnLutImage(int colorOnXCoordinate, int colorOnYCoordinate,
                                               int colorOnZCoordinate) {
        int rowDepth = rowDepth();
        final int z_XDepth = rowDepth == 1 ? colorOnZCoordinate : colorOnZCoordinate % rowDepth;
        final int z_YDepth = rowDepth == 1 ? 0 : colorOnZCoordinate / rowDepth;
        int lutX = z_XDepth * sideSize + colorOnXCoordinate;
        int lutY = z_YDepth * sideSize + colorOnYCoordinate;
        return new Point(lutX, lutY);
    }

}
