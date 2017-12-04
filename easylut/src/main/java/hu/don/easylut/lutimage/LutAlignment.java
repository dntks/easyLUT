package hu.don.easylut.lutimage;


public interface LutAlignment {

    String TAG = LutAlignment.class.getSimpleName();

    int getX(int rowDepth, int sideSize, int x, int y, int z);

    int getY(int rowDepth, int sideSize, int x, int y, int z);

    class Square implements LutAlignment {

        @Override
        public int getX(int rowDepth, int sideSize, int x, int y, int z) {
            int lutX = (rowDepth == 1 ? z : z % rowDepth) * sideSize + x;
//            Log.d(TAG, String.format("getX: (%d,%d,%d) @ %d --> %d", x, y, z, sideSize, lutX));
            return lutX;
        }

        @Override
        public int getY(int rowDepth, int sideSize, int x, int y, int z) {
            int lutY = (rowDepth == 1 ? 0 : z / rowDepth) * sideSize + y;
//            Log.d(TAG, String.format("getY: (%d,%d,%d) @ %d --> %d", x, y, z, sideSize, lutY));
            return lutY;
        }

    }

    class Hald implements LutAlignment {

        @Override
        public int getX(int rowDepth, int sideSize, int x, int y, int z) {
            int red = x;
            int green = Math.max(0, ((y + 1) / rowDepth) - 1) * sideSize;
            int lutX = red + green;
//            Log.d(TAG, String.format("getX: (%d,%d,%d) @ %d --> %d", x, y, z, sideSize, lutX));
            return lutX;
        }

        @Override
        public int getY(int rowDepth, int sideSize, int x, int y, int z) {
            int green = y % rowDepth;
            int blue = z * rowDepth;
            int lutY = green + blue;
//            Log.d(TAG, String.format("getY: (%d,%d,%d) @ %d --> %d", x, y, z, sideSize, lutY));
            return lutY;
        }

    }

    enum Mode {
        SQUARE {
            @Override
            LutAlignment getLutAlignment() {
                return new Square();
            }
        },
        HALD {
            @Override
            LutAlignment getLutAlignment() {
                return new Hald();
            }
        };

        abstract LutAlignment getLutAlignment();
    }
}
