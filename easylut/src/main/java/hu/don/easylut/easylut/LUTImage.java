package hu.don.easylut.easylut;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;

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

    private int getPixelByIndex(int lutIndex) {
        int red = ((lutColors[lutIndex] >> 16) & 0xff);
        int green = ((lutColors[lutIndex] >> 8) & 0xff);
        int blue = ((lutColors[lutIndex]) & 0xff);
        return 0xff000000 | (red << 16) | (green << 8) | blue;
    }

    private int getLutPixelIndex(int pixelColor) {
        DistortedColor distortedColor = new DistortedColor(pixelColor);
        Point point = getPointCoordinateOnLutImage(distortedColor.getColorOnXCoordinate(), distortedColor.getColorOnYCoordinate(), distortedColor.getColorOnZCoordinate());
        return point.y * lutWidth + point.x;
    }

    private Point getPointCoordinateOnLutImage(int colorOnXCoordinate, int colorOnYCoordinate, int colorOnZCoordinate) {
        int rowDepth = rowDepth();
        final int z_XDepth = rowDepth == 1 ? colorOnZCoordinate : colorOnZCoordinate % rowDepth;
        final int z_YDepth = rowDepth == 1 ? 0 : colorOnZCoordinate / rowDepth;
        int lutX = z_XDepth * sideSize + colorOnXCoordinate;
        int lutY = z_YDepth * sideSize + colorOnYCoordinate;
        return new Point(lutX, lutY);
    }

    public class DistortedColor {
        public final int distortedRed;
        public final int distortedGreen;
        public final int distortedBlue;

        public DistortedColor(int pixelColor) {
            distortedRed = Color.red(pixelColor) / rgbDistortion;
            distortedGreen = Color.green(pixelColor) / rgbDistortion;
            distortedBlue = Color.blue(pixelColor) / rgbDistortion;
        }

        public int getColorOnXCoordinate() {
            int xOnStrongest = getPixelByIndex(sideSize - 1);
            return returnStrongest(xOnStrongest);
        }

        public int getColorOnYCoordinate() {
            int yIndex = lutWidth * (sideSize - 1);
            int yOnStrongest = getPixelByIndex(yIndex);
            return returnStrongest(yOnStrongest);
        }

        public int getColorOnZCoordinate() {
            int columnDepth = columnDepth();
            int X = (columnDepth - 1) * sideSize + 1;
            int Y = (rowDepth() - 1) * sideSize + 1;
            int xOnStrongest = getPixelByIndex(Y * lutWidth + X);
            return returnStrongest(xOnStrongest);
        }

        private int returnStrongest(int pixel) {
            if (redIsStrongestOnPixel(pixel)) {
                return distortedRed;
            } else if (greenIsStrongestOnPixel(pixel)) {
                return distortedGreen;
            } else {
                return distortedBlue;
            }
        }

        private boolean greenIsStrongestOnPixel(int color) {
            int green = Color.green(color);
            int red = Color.red(color);
            int blue = Color.blue(color);
            return green > red &&
                    green > blue;
        }

        private boolean redIsStrongestOnPixel(int color) {
            int red = Color.red(color);
            int green = Color.green(color);
            int blue = Color.blue(color);
            return red > green &&
                    red > blue;
        }
    }
}
