package hu.don.easylut.lutimage;


public interface CoordinateToColor {

    boolean isRedMappedToX();

    boolean isRedMappedToY();

    boolean isRedMappedToZ();

    boolean isGreenMappedToX();

    boolean isGreenMappedToY();

    boolean isGreenMappedToZ();

    enum Type {
        GUESS_AXES {
            @Override
            CoordinateToColor getCoordinateToColor(LUTImage lutImage) {
                return new GuessCoordinateToColor(lutImage);
            }
        }, RGB_TO_XYZ {
            @Override
            CoordinateToColor getCoordinateToColor(LUTImage lutImage) {
                return new RgbToXyz();
            }
        }, RGB_TO_XZY {
            @Override
            CoordinateToColor getCoordinateToColor(LUTImage lutImage) {
                return new RgbToXzy();
            }
        }, RGB_TO_YZX {
            @Override
            CoordinateToColor getCoordinateToColor(LUTImage lutImage) {
                return new RgbToYzx();
            }
        }, RGB_TO_YXZ {
            @Override
            CoordinateToColor getCoordinateToColor(LUTImage lutImage) {
                return new RgbToYxz();
            }
        }, RGB_TO_ZXY {
            @Override
            CoordinateToColor getCoordinateToColor(LUTImage lutImage) {
                return new RgbToZxy();
            }
        }, RGB_TO_ZYX {
            @Override
            CoordinateToColor getCoordinateToColor(LUTImage lutImage) {
                return new RgbToZyx();
            }
        };

        abstract CoordinateToColor getCoordinateToColor(LUTImage lutImage);
    }
}
