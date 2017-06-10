package hu.don.easylut.easylutproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import hu.don.easylut.EasyLUT;
import hu.don.easylut.filter.Filter;
import hu.don.easylut.lutimage.CoordinateToColor;


public class MainActivity extends AppCompatActivity {
    private final List<Filter> effectItems = new LinkedList<>();
    private int currentFilter = 0;
    private ImageView imageView;
    private Bitmap originalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        Resources resources = getResources();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                originalBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                final int measuredHeight = imageView.getMeasuredHeight();
                final int measuredWidth = imageView.getMeasuredWidth();
                if (originalBitmap.getHeight() >= measuredHeight || originalBitmap.getWidth() >= measuredWidth) {
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    originalBitmap = Bitmap.createScaledBitmap(originalBitmap, measuredWidth, measuredHeight, true);
                }
            }
        });

        effectItems.add(EasyLUT.createNonFilter());
        effectItems.add(EasyLUT.fromResourceId().withColorAxes(CoordinateToColor.Type.RGB_TO_ZYX).withResources(resources).withLutBitmapId(R.drawable.anotherlut).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.lut2).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.lut3).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_01).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_02).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_03).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_04).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_05).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_06).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_07).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_08).createFilter());
        effectItems.add(EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.pnglut_small_1).createFilter());
    }

    public void clickOnImage(View view) {
        currentFilter++;
        if (currentFilter >= effectItems.size()) {
            currentFilter = 0;
        }
        final Filter filter = effectItems.get(currentFilter);
        final Bitmap bitmap = filter.apply(originalBitmap);
        imageView.setImageBitmap(bitmap);
    }

}
