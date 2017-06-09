package hu.don.easylut.lutimage;


public class RgbToYxz implements CoordinateToColor {
    @Override
    public boolean isRedMappedToX() {
        return false;
    }

    @Override
    public boolean isRedMappedToY() {
        return true;
    }

    @Override
    public boolean isRedMappedToZ() {
        return false;
    }

    @Override
    public boolean isGreenMappedToX() {
        return true;
    }

    @Override
    public boolean isGreenMappedToY() {
        return false;
    }

    @Override
    public boolean isGreenMappedToZ() {
        return false;
    }
}
