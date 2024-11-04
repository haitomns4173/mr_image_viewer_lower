package com.haitomns.arnaventerprise;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// SingleImageFullscreenActivity.java
public class SingleImageFullscreenActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int startPosition;
    Context context = this;
    List<Bitmap> categoryImages; // List of bitmaps for images in the selected category
    List<String> imageNames;     // List of names for each image in the selected category
    String folderPath;
    String[] imageCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image_fullscreen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        viewPager = findViewById(R.id.viewPager);

        // Retrieve the image collection and folder path from the intent
        folderPath = getIntent().getStringExtra("folderPath");
        imageCollection = getIntent().getStringArrayExtra("imageCollection");
        startPosition = getIntent().getIntExtra("position", 0);

        loadCategoryImages(folderPath, imageCollection);
        setupViewPager();
    }

    private void loadCategoryImages(String folderPath, String[] imageCollection) {
        categoryImages = new ArrayList<>();
        for (String imageName : imageCollection) {
            try {
                AssetManager assetManager = getAssets();
                InputStream is = assetManager.open(folderPath + "/" + imageName);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                categoryImages.add(bitmap);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupViewPager() {
        ImagePagerAdapter adapter = new ImagePagerAdapter(context, categoryImages, imageNames, folderPath, imageCollection);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(startPosition); // Start from the clicked image
    }
}
