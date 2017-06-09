package hu.don.easylut.lutimage;

import android.graphics.Color;

public class DistortedColor {
    private LUTImage lutImage;
    public final int distortedRed;
    public final int distortedGreen;
    public final int distortedBlue;

    public DistortedColor(LUTImage lutImage, int pixelColor) {
        this.lutImage = lutImage;
        distortedRed = Color.red(pixelColor) / lutImage.rgbDistortion;
        distortedGreen = Color.green(pixelColor) / lutImage.rgbDistortion;
        distortedBlue = Color.blue(pixelColor) / lutImage.rgbDistortion;
    }

    public int getColorOnXCoordinate() {
        if (lutImage.coordinateToColor.isRedMappedToX()) {
            return distortedRed;
        } else if (lutImage.coordinateToColor.isGreenMappedToX()) {
            return distortedGreen;
        } else {
            return distortedBlue;
        }
    }

    public int getColorOnYCoordinate() {
        if (lutImage.coordinateToColor.isRedMappedToY()) {
            return distortedRed;
        } else if (lutImage.coordinateToColor.isGreenMappedToY()) {
            return distortedGreen;
        } else {
            return distortedBlue;
        }
    }

    public int getColorOnZCoordinate() {
        if (lutImage.coordinateToColor.isRedMappedToZ()) {
            return distortedRed;
        } else if (lutImage.coordinateToColor.isGreenMappedToZ()) {
            return distortedGreen;
        } else {
            return distortedBlue;
        }
    }

}
