package hu.don.easylut.easylutproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import hu.don.easylut.EasyLUT;
import hu.don.easylut.filter.Filter;
import hu.don.easylut.lutimage.CoordinateToColor;


public class MainActivity extends AppCompatActivity implements FilterAdapter.OnFilterSelected {

    private ImageView ivImage;
    private TextView tvName;
    private ProgressBar pbBusy;
    private RecyclerView rvFilters;

    private final List<FilterSelection> effectItems = new LinkedList<>();
    private Bitmap originalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tv_name);
        ivImage = findViewById(R.id.iv_image);
        pbBusy = findViewById(R.id.pb_busy);
        Resources resources = getResources();
        ivImage.post(new Runnable() {
            @Override
            public void run() {
                originalBitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
                final int measuredHeight = ivImage.getMeasuredHeight();
                final int measuredWidth = ivImage.getMeasuredWidth();
                if (originalBitmap.getHeight() >= measuredHeight || originalBitmap.getWidth() >= measuredWidth) {
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    originalBitmap = Bitmap.createScaledBitmap(originalBitmap, measuredWidth, measuredHeight, true);
                }
                onFilterClicked(effectItems.get(0));
            }
        });

        rvFilters = findViewById(R.id.rv_filters);
        addFilter("none", EasyLUT.createNonFilter());
        addFilter("anotherlut", EasyLUT.fromResourceId().withColorAxes(CoordinateToColor.Type.RGB_TO_ZYX).withResources(resources).withLutBitmapId(R.drawable.anotherlut).createFilter());
        addFilter("lut2", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.lut2).createFilter());
        addFilter("lut3", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.lut3).createFilter());
        addFilter("filter_lut_01", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_01).createFilter());
        addFilter("filter_lut_02", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_02).createFilter());
        addFilter("filter_lut_03", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_03).createFilter());
        addFilter("filter_lut_04", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_04).createFilter());
        addFilter("filter_lut_05", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_05).createFilter());
        addFilter("filter_lut_06", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_06).createFilter());
        addFilter("filter_lut_07", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_07).createFilter());
        addFilter("filter_lut_08", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_lut_08).createFilter());
        addFilter("pnglut_small_1", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.pnglut_small_1).createFilter());

        rvFilters.setLayoutManager(new LinearLayoutManager(this));
        rvFilters.setAdapter(new FilterAdapter(effectItems, this));
    }

    private void addFilter(String name, Filter filter) {
        effectItems.add(new FilterSelection(name, filter));
    }

    @Override
    public void onFilterClicked(final FilterSelection filterSelection) {
        tvName.setText(filterSelection.name);
        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected void onPreExecute() {
                pbBusy.setVisibility(View.VISIBLE);
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {
                return filterSelection.filter.apply(originalBitmap);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                ivImage.setImageBitmap(bitmap);
                pbBusy.setVisibility(View.GONE);
            }
        }.execute();
    }

}
