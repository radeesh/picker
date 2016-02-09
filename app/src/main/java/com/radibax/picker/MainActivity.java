package com.radibax.picker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //URL url = new URL("http://www.planwallpaper.com/static/images/Winter-Tiger-Wild-Cat-Images.jpg");
        //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setImageResource(R.drawable.img);

    }

     public void saveImage(View v) {
         // Perform action on click
         String filename = "test";
         Log.v("saveImage", "called");
         mImageView.buildDrawingCache();
         Bitmap bmp = mImageView.getDrawingCache();

         File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //context.getExternalFilesDir(null);
         //File storageLoc = Environment.getRootDirectory();
         File file = new File(storageLoc+ "/" + filename + ".jpg");

         try {
             FileOutputStream fos = new FileOutputStream(file);
             bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
             fos.close();

             //scanFile(context, Uri.fromFile(file));
             Log.v("saveImage",file.getAbsolutePath() + file.exists());

         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

         sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                 + Environment.getExternalStorageDirectory())));
     }

         private static void scanFile(Context context, Uri imageUri){
             Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
             scanIntent.setData(imageUri);
             context.sendBroadcast(scanIntent);

         }
}
