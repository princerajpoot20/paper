package com.example.paper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.paper.Models.Photo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class WallpaperActivity extends AppCompatActivity {

    ImageView imageView_wallpaper;
    FloatingActionButton fab_download,fab_wallpaper;
    Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        imageView_wallpaper = findViewById(R.id.imageView_wallpaper);
        fab_download = findViewById(R.id.fab_download);
        fab_wallpaper = findViewById(R.id.fab_wallpaper);

        photo = (Photo) getIntent().getSerializableExtra("Photo");

        Picasso.get().load(photo.getSrc().getOriginal()).placeholder(R.drawable.placeholder).into(imageView_wallpaper);

        fab_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadManager downloadManager = null;
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

                Uri uri = Uri.parse(photo.getSrc().getLarge());

                DownloadManager.Request request = new DownloadManager.Request(uri);

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("Paper_"+photo.getPhotographer())
                        .setMimeType("image/jpeg")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,"Paper_"+photo.getPhotographer()+".jpg");

                downloadManager.enqueue(request);

                Toast.makeText(WallpaperActivity.this,"Hurray!",Toast.LENGTH_SHORT).show();
            }
        });

        fab_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(WallpaperActivity.this);
                Bitmap bitmap = ((BitmapDrawable) imageView_wallpaper.getDrawable()).getBitmap();

                try {
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(WallpaperActivity.this, "Wallpaper Set!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(WallpaperActivity.this, "Better Luck Next Time", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}