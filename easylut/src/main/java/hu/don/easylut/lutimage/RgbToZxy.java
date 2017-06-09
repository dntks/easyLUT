package hu.don.easylut.lutimage;


public class RgbToZxy implements CoordinateToColor {
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
