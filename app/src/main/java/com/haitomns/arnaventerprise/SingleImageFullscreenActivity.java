package com.haitomns.arnaventerprise;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import com.github.chrisbanes.photoview.PhotoView;
import java.io.IOException;
import java.io.InputStream;

public class SingleImageFullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image_fullscreen);

        PhotoView imageView = findViewById(R.id.fullscreenImageView);
        String imageName = getIntent().getStringExtra("imageName");
        String folderPath = getIntent().getStringExtra("folderPath");

        if (imageName != null && folderPath != null) {
            loadImage(imageName, folderPath, imageView);
        }
    }

    private void loadImage(String imageName, String folderPath, PhotoView imageView) {
        try {
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open(folderPath + "/" + imageName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bitmap);
            is.close(); // Close the stream after loading the image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
