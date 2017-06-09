package hu.don.easylut.lutimage;


public class RgbToXyz implements CoordinateToColor{

    @Override
    public boolean isRedMappedToX() {
        return true;
    }

    @Override
    public boolean isRedMappedToY() {
        return false;
    }

    @Override
    public boolean isRedMappedToZ() {
        return false;
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
