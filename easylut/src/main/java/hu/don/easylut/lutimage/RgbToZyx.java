package hu.don.easylut.lutimage;


public class RgbToZyx implements CoordinateToColor {
    @Override
    public boolean isRedMappedToX() {
        return false;
    }

    @Override
    public boolean isRedMappedToY() {
        return false;
    }

    @Override
    public boolean isRedMappedToZ() {
        return true;
    }

    @Override
    public boolean isGreenMappedToX() {
        return false;
    }

    @Override
    public boolean isGreenMappedToY() {
        return true;
    }

    @Override
    public boolean isGreenMappedToZ() {
        return false;
    }
}
