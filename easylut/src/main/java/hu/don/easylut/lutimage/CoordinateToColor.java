package hu.don.easylut.lutimage;


public interface CoordinateToColor {

    class RgbToXyz implements CoordinateToColor {
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

    class RgbToXzy implements CoordinateToColor {
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
            return false;
        }

        @Override
        public boolean isGreenMappedToZ() {
            return true;
        }
    }

    class RgbToYzx implements CoordinateToColor {
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
            return false;
        }

        @Override
        public boolean isGreenMappedToY() {
            return false;
        }

        @Override
        public boolean isGreenMappedToZ() {
            return true;
        }
    }

    class RgbToYxz implements CoordinateToColor {
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

    class RgbToZxy implements CoordinateToColor {
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

    class RgbToZyx implements CoordinateToColor {
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
