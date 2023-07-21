package com.example.chgwall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button chgwall;
    RelativeLayout relativeLayout;
    Handler handler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUIElement();
        chgwall.setOnClickListener(view -> {
            try{
                handler.postDelayed(selectedImage,1000);
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, "Exception", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindUIElement() {
        imageView= findViewById(R.id.image);
        chgwall= findViewById(R.id.button);
        relativeLayout = findViewById(R.id.relativeLayout);
    }
    private Runnable selectedImage = new Runnable() {
        @Override
        public void run() {
            try{
                AssetManager assetManager = getAssets();
                String[] wallpaperF=assetManager.list("wallpaper");
                int randomN = new Random().nextInt(wallpaperF.length);
                InputStream inputStream = assetManager.open("wallpaper/"+wallpaperF[randomN]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                relativeLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
                handler.postDelayed(this,5000);
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}