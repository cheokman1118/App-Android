package com.jjke.imageviewer;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AssetManager assetManager = getAssets();

                 File copyTarget = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()+"/YM.jpg");
               if (copyTarget.exists()) copyTarget.delete();
                try {
                    copyTarget.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    InputStream fis = assetManager.open("YM.jpg");
                    OutputStream fos = new FileOutputStream(copyTarget);
                    byte[] buffer = new byte[4096];
                    while (fis.read(buffer) > 0) {
                        fos.write(buffer);
                    }
                    fis.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(copyTarget.toURI().toString()),"image/*");
                startActivity(intent);
            }
        });
    }
}
