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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import hu.don.easylut.EasyLUT;
import hu.don.easylut.filter.Filter;
import hu.don.easylut.lutimage.CoordinateToColor;


public class MainActivity extends AppCompatActivity implements FilterAdapter.OnFilterSelected {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final boolean RESIZE_BITMAP = true;

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
                updateBitmap();
            }
        });

        rvFilters = findViewById(R.id.rv_filters);
        addFilter("none", EasyLUT.createNonFilter());
        addFilter("identity", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.identity).createFilter());
        addFilter("identity_hald", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.identity_hald).createFilter());
        addFilter("square_8_00", EasyLUT.fromResourceId().withColorAxes(CoordinateToColor.Type.RGB_TO_ZYX).withResources(resources).withLutBitmapId(R.drawable.filter_square_8_00).createFilter());
        addFilter("square_8_01", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_01).createFilter());
        addFilter("square_8_02", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_02).createFilter());
        addFilter("square_8_03", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_03).createFilter());
        addFilter("square_8_04", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_04).createFilter());
        addFilter("square_8_05", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_05).createFilter());
        addFilter("square_8_06", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_06).createFilter());
        addFilter("square_8_07", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_07).createFilter());
        addFilter("square_8_08", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_08).createFilter());
        addFilter("square_8_09", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_09).createFilter());
        addFilter("square_4_00", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_4_00).createFilter());
        addFilter("square_8_vga", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_vga).createFilter());
        addFilter("square_8_ega", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_ega).createFilter());
        addFilter("square_8_nintendo", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_nintendo).createFilter());
        addFilter("square_8_sega", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_square_8_sega).createFilter());
        addFilter("wide_4_00", EasyLUT.fromResourceId().withResources(resources).withLutBitmapId(R.drawable.filter_wide_4_00).createFilter());

        rvFilters.setLayoutManager(new LinearLayoutManager(this));
        rvFilters.setAdapter(new FilterAdapter(effectItems, this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_identity:
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    ivImage.setImageResource(R.drawable.identity);
                } else {
                    ivImage.setImageResource(R.drawable.landscape);
                }
                updateBitmap();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateBitmap() {
        originalBitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
        if (RESIZE_BITMAP) {
            int measuredHeight = ivImage.getMeasuredHeight();
            int measuredWidth = ivImage.getMeasuredWidth();
            if (measuredWidth != 0 && measuredHeight != 0 && (originalBitmap.getHeight() >= measuredHeight || originalBitmap.getWidth() >= measuredWidth)) {
                float originalRatio = (float) originalBitmap.getWidth() / (float) originalBitmap.getHeight();
                float measuredRatio = (float) measuredWidth / (float) measuredHeight;
                if (originalRatio > measuredRatio) {
                    measuredWidth = (int) (measuredHeight * originalRatio);
                } else {
                    measuredHeight = (int) (measuredWidth / originalRatio);
                }
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                originalBitmap = Bitmap.createScaledBitmap(originalBitmap, measuredWidth, measuredHeight, true);
            }
        }
        onFilterClicked(effectItems.get(0));
    }

    private void addFilter(String name, Filter filter) {
        effectItems.add(new FilterSelection(name.toUpperCase(Locale.ENGLISH), filter));
    }

    @Override
    public void onFilterClicked(final FilterSelection filterSelection) {
        tvName.setText(filterSelection.name);
        new AsyncTask<Void, Void, Bitmap>() {

            long start;

            @Override
            protected void onPreExecute() {
                pbBusy.animate().alpha(1f).start();
                pbBusy.setVisibility(View.VISIBLE);
                ivImage.animate().alpha(0.5f).start();
                start = System.nanoTime();
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {
                return filterSelection.filter.apply(originalBitmap);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                ivImage.setImageBitmap(bitmap);
                ivImage.animate().alpha(1f).start();
                pbBusy.animate().alpha(0f).start();
                Log.d(TAG, String.format("processed bitmap in %.2fms", (System.nanoTime() - start) / 1e6f));
            }
        }.execute();
    }

}
