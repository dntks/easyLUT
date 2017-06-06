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

import hu.don.easylut.easylut.Filter;
import hu.don.easylut.easylut.FilterNon;
import hu.don.easylut.easylut.LUTFilter;


public class MainActivity extends AppCompatActivity {
    List<Filter> effectItems = new LinkedList<>();
    int currentFilter = 0;
    int currentFilter2 = 0;
    ImageView imageView;
    ImageView imageView2;
    Bitmap originalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        imageView2 = (ImageView) findViewById(R.id.image2);
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

        effectItems.add(new FilterNon());
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_01, R.drawable.pnglut_small_1));
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_02, R.drawable.pnglut_small_2));
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_03, R.drawable.pnglut_small_3));
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_04, R.drawable.pnglut_small_4));
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_05, R.drawable.pnglut_small_5));
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_06, R.drawable.pnglut_small_6));
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_07, R.drawable.pnglut_small_7));
        effectItems.add(new LUTFilter(resources, R.drawable.filter_lut_08, R.drawable.pnglut_small_8));

    }

    public void clickOnImage(View view) {
        currentFilter++;
        if (currentFilter >= effectItems.size()) {
            currentFilter = 0;
        }
        final Filter filter = effectItems.get(currentFilter);
        final Bitmap bitmap = filter.applyFilterToBitmap(originalBitmap);
        imageView.setImageBitmap(bitmap);
    }

    public void clickOnSecondImage(View view) {
        currentFilter2++;
        if (currentFilter2 >= effectItems.size()) {
            currentFilter2 = 0;
        }
        final Filter filter = effectItems.get(currentFilter2);
        final Bitmap bitmap = filter.applySmallFilterToBitmap(originalBitmap);
        imageView2.setImageBitmap(bitmap);
    }
}
