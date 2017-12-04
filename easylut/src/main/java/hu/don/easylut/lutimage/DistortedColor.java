package hu.don.easylut.lutimage;

import android.graphics.Color;

public class DistortedColor {

    public static int getColorOnXCoordinate(LUTImage lutImage, int pixelColor) {
        if (lutImage.coordinateToColor.isRedMappedToX()) {
            return getRed(lutImage, pixelColor);
        } else if (lutImage.coordinateToColor.isGreenMappedToX()) {
            return getGreen(lutImage, pixelColor);
        } else {
            return getBlue(lutImage, pixelColor);
        }
    }

    public static int getColorOnYCoordinate(LUTImage lutImage, int pixelColor) {
        if (lutImage.coordinateToColor.isRedMappedToY()) {
            return getRed(lutImage, pixelColor);
        } else if (lutImage.coordinateToColor.isGreenMappedToY()) {
            return getGreen(lutImage, pixelColor);
        } else {
            return getBlue(lutImage, pixelColor);
        }
    }

    public static int getColorOnZCoordinate(LUTImage lutImage, int pixelColor) {
        if (lutImage.coordinateToColor.isRedMappedToZ()) {
            return getRed(lutImage, pixelColor);
        } else if (lutImage.coordinateToColor.isGreenMappedToZ()) {
            return getGreen(lutImage, pixelColor);
        } else {
            return getBlue(lutImage, pixelColor);
        }
    }

    private static int getBlue(LUTImage lutImage, int pixelColor) {
        return Color.blue(pixelColor) / lutImage.rgbDistortion;
    }

    private static int getGreen(LUTImage lutImage, int pixelColor) {
        return Color.green(pixelColor) / lutImage.rgbDistortion;
    }

    private static int getRed(LUTImage lutImage, int pixelColor) {
        return Color.red(pixelColor) / lutImage.rgbDistortion;
    }

}
