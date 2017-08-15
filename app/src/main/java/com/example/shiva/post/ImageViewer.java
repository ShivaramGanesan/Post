package com.example.shiva.post;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewer extends AppCompatActivity {
    ImageView displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        displayImage = (ImageView) findViewById(R.id.thisisimage);
        //Bitmap bmp = (Bitmap)displayImage.getParcelableExtra("BitmapImage");
        Bitmap bitmap=(Bitmap)getIntent().getParcelableExtra("ImageBitmap");
        displayImage.setImageBitmap(bitmap);
    }
}
