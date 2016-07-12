package com.jike.anotherzoom;

import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private ScaleGestureDetector scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        scale = new ScaleGestureDetector(this, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                iv.setScaleX(iv.getScaleX()*detector.getScaleFactor());
                iv.setScaleY(iv.getScaleY()*detector.getScaleFactor());
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return scale.onTouchEvent(event);
    }
}
