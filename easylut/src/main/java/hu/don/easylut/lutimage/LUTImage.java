package hu.don.easylut.lutimage;


import android.graphics.Bitmap;
import android.util.Log;

public class LUTImage {

    private static final String TAG = LUTImage.class.getSimpleName();

    private static final int COLOR_DEPTH = 256;

    private final int lutColors[];

    protected final int lutWidth;
    protected final int lutHeight;
    protected final int sideSize;
    protected final int rowDepth;
    protected final int columnDepth;
    protected final int rgbDistortion;
    protected CoordinateToColor coordinateToColor;
    protected LutAlignment lutAlignment;

    private LUTImage(int lutWidth, int lutHeight, int[] lutColors,
                     CoordinateToColor.Type coordinateToColorType,
                     LutAlignment.Mode lutAlignmentMode) {
        this.lutWidth = lutWidth;
        this.lutHeight = lutHeight;
        this.lutColors = lutColors;
        this.sideSize = sideSize();
        this.rowDepth = lutHeight / sideSize;
        this.columnDepth = lutWidth / sideSize;
        this.rgbDistortion = COLOR_DEPTH / sideSize;
        coordinateToColor = coordinateToColorType.getCoordinateToColor(this);
        lutAlignment = lutAlignmentMode.getLutAlignment();
        Log.d(TAG, "LUTImage: " + toString());
    }

    public static LUTImage createLutImage(Bitmap lutBitmap,
                                          CoordinateToColor.Type coordinateToColorType,
                                          LutAlignment.Mode lutAlignmentMode) {
        int lutWidth = lutBitmap.getWidth();
        int lutColors[] = new int[lutWidth * lutBitmap.getHeight()];
        lutBitmap.getPixels(lutColors, 0, lutWidth, 0, 0, lutWidth, lutBitmap.getHeight());
        LUTImage lutImage;
        lutImage = new LUTImage(lutWidth, lutBitmap.getHeight(), lutColors,
                coordinateToColorType, lutAlignmentMode);

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

    public int getColorPixelOnLut(int pixelColor) {
        int lutIndex = getLutPixelIndex(pixelColor);
        return getPixelByIndex(lutIndex);
    }

    int getPixelByIndex(int lutIndex) {
        int red = ((lutColors[lutIndex] >> 16) & 0xff);
        int green = ((lutColors[lutIndex] >> 8) & 0xff);
        int blue = ((lutColors[lutIndex]) & 0xff);
//        Log.d(TAG, String.format("getPixelByIndex: %d: %d,%d,%d", lutIndex, red, green, blue));
        return 0xff000000 | (red << 16) | (green << 8) | blue;
    }

    private int getLutPixelIndex(int pixelColor) {
        int x = DistortedColor.getColorOnXCoordinate(this, pixelColor);
        int y = DistortedColor.getColorOnYCoordinate(this, pixelColor);
        int z = DistortedColor.getColorOnZCoordinate(this, pixelColor);
        int lutX = lutAlignment.getX(rowDepth, sideSize, x, y, z);
        int lutY = lutAlignment.getY(rowDepth, sideSize, x, y, z);
        int index = lutY * lutWidth + lutX;
//        Log.d(TAG, String.format("getLutPixelIndex: z=%d --> %d,%d --> %d", z, lutX, lutY, index));
        return index;
    }

    @Override
    public String toString() {
        return "LUTImage{" +
                "lutWidth=" + lutWidth +
                ", lutHeight=" + lutHeight +
                ", sideSize=" + sideSize +
                ", coordinateToColor=" + coordinateToColor +
                ", lutAlignment=" + lutAlignment +
                '}';
    }

}
