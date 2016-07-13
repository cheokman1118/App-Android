package com.jjke.imageviewer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Image_act extends AppCompatActivity {
    private TextView tv;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_act);
        tv = (TextView) findViewById(R.id.tv_img);
        imageView = (ImageView) findViewById(R.id.iv);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri==null){
            tv.setText("無法顯示圖像");
        }else {
            imageView.setImageURI(uri);
        }
    }
}
