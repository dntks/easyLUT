With easyLUT you can apply several type of LUT filters to your Bitmaps and ImageViews easily.

EasyLUT is available on jCenter:

`compile 'hu.don.easylut:easylut:0.4'`

 In the https://github.com/dntks/easyLUT/tree/master/app you can check an example usage of the library.

Example usage with LUT resouce id:

    Filter filter = EasyLUT.fromResourceId()
                           .withResources(resources)
                           .withLutBitmapId(R.drawable.filter_lut_01)
                           .createFilter();
    Bitmap filteredBitmap = filter.apply(originalBitmap);

Example usage with LUT bitmap:

    Filter filter = EasyLUT.fromBitmap()
                           .withBitmap(lutBitmap) //lutBitmap is created by you before
                           .createFilter()
    Bitmap filteredBitmap = filter.apply(originalBitmap);

When calling with resources it won't load the bitmap into memory until using the bitmap, and the LUT's bitmap won't stay in memory after usage. However it will reload the bitmap every time you use the LUT filter.

When calling with bitmap, the LUTFilter object will have a reference to the bitmap object, but it won't load the bitmap every time you use it.

Compatibility with file access usage of LUT bitmap is planned. Any other suggestions are welcome.

Compatibility tested for the following LUT image types:
 *  64x64x64 LUT - 2D image is 512*512 - usual RGB dimensions: Red on X axis, Green on Y axis, Blue on Z axis.

 ![lut64](https://raw.githubusercontent.com/dntks/easyLUT/master/app/src/main/res/drawable-nodpi/anotherlut.png)
 *  16x16x16 inverted LUT - 2D image is 64*64 - inverted RGB dimensions: Blue on X axis, Red on Y axis, Green on Z axis.

 ![inverted 16x16x16 LUT](https://github.com/dntks/easyLUT/blob/master/app/src/main/res/drawable-nodpi/lut2.png?raw=true)
 *  16x16x16 LUT - 2D image is 256*16 - usual RGB dimensions: Red on X axis, Green on Y axis, Blue on Z axis.

 ![One lined 16x16x16 LUT](https://github.com/dntks/easyLUT/blob/master/app/src/main/res/drawable-nodpi/pnglut_small_1.png?raw=true)

 The RGB dimensions are now guessed by the library, assuming normal color schemes:
 Checking the RGB values in LUT cube's 3 parts on the XYZ axes farthest parts.

 If you come across any other LUT types which is not handled well by the library please create a ticket.

For inverted color schemes, for example when you want red pixel to become blueish ones, I'll update the library with configurable axis scheme.

Full usage:

    EasyLUT.fromResourceId()
           .withResources(resources)
           .withLutBitmapId(R.drawable.filter_lut_01)
           .withColorAxes(CoordinateToColor.Type.RGB_TO_XYZ)          //default is GUESS_AXES
           .withStrategy(BitmapStrategy.Type.APPLY_ON_ORIGINAL_BITMAP)//default is CREATING_NEW_BITMAP
           .createFilter();
