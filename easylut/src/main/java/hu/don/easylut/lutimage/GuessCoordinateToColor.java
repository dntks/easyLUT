package hu.don.easylut.lutimage;

import android.graphics.Color;

public class GuessCoordinateToColor implements CoordinateToColor{
    private final boolean isRedMappedToX;
    private final boolean isRedMappedToY;
    private final boolean isRedMappedToZ;
    private final boolean isGreenMappedToX;
    private final boolean isGreenMappedToY;
    private final boolean isGreenMappedToZ;
    private final LUTImage lutImage;

    public GuessCoordinateToColor(LUTImage lutImage) {
        this.lutImage = lutImage;
        isRedMappedToX = calculateRedMappedToX();
        isRedMappedToY = calculateRedMappedToY();
        isGreenMappedToX = calculateGreenMappedToX();
        isGreenMappedToY = calculateGreenMappedToY();
        isRedMappedToZ = calculateRedMappedToZ();
        isGreenMappedToZ = calculateGreenMappedToZ();
    }

    public boolean isRedMappedToX() {
        return isRedMappedToX;
    }

    public boolean isRedMappedToY() {
        return isRedMappedToY;
    }

    public boolean isRedMappedToZ() {
        return isRedMappedToZ;
    }

    public boolean isGreenMappedToX() {
        return isGreenMappedToX;
    }

    public boolean isGreenMappedToY() {
        return isGreenMappedToY;
    }

    public boolean isGreenMappedToZ() {
        return isGreenMappedToZ;
    }

    private boolean calculateRedMappedToX() {
        int xOnStrongest = lutImage.getPixelByIndex(lutImage.sideSize - 1);
        return redIsStrongestOnPixel(xOnStrongest);
    }

    public boolean calculateRedMappedToY() {
        int yIndex = lutImage.lutWidth * (lutImage.sideSize - 1);
        int yOnStrongest = lutImage.getPixelByIndex(yIndex);
        return redIsStrongestOnPixel(yOnStrongest);
    }

    public boolean calculateRedMappedToZ() {
        int columnDepth = lutImage.columnDepth();
        int X = (columnDepth - 1) * lutImage.sideSize + 1;
        int Y = (lutImage.rowDepth() - 1) * lutImage.sideSize + 1;
        int xOnStrongest = lutImage.getPixelByIndex(Y * lutImage.lutWidth + X);
        return redIsStrongestOnPixel(xOnStrongest);
    }

    public boolean calculateGreenMappedToX() {
        int xOnStrongest = lutImage.getPixelByIndex(lutImage.sideSize - 1);
        return greenIsStrongestOnPixel(xOnStrongest);
    }

    public boolean calculateGreenMappedToY() {
        int yIndex = lutImage.lutWidth * (lutImage.sideSize - 1);
        int yOnStrongest = lutImage.getPixelByIndex(yIndex);
        return greenIsStrongestOnPixel(yOnStrongest);
    }

    public boolean calculateGreenMappedToZ() {
        int columnDepth = lutImage.columnDepth();
        int X = (columnDepth - 1) * lutImage.sideSize + 1;
        int Y = (lutImage.rowDepth() - 1) * lutImage.sideSize + 1;
        int xOnStrongest = lutImage.getPixelByIndex(Y * lutImage.lutWidth + X);
        return greenIsStrongestOnPixel(xOnStrongest);
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
