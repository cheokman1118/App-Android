package com.jike.twofingerzoom;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private ImageView scaleImage;
    private float old_x_0, old_x_1, old_y_0, old_y_1, new_x_0, new_x_1, new_y_0, new_y_1;
    private float distance, newDistance;
    private float scale = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.image);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionTag = MotionEventCompat.getActionMasked(event);
        if (MotionEventCompat.getPointerCount(event) == 2) {
            switch (actionTag) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    old_x_0 = MotionEventCompat.getX(event, 0);
                    old_x_1 = MotionEventCompat.getY(event, 0);
                    old_y_0 = MotionEventCompat.getY(event, 1);
                    old_y_1 = MotionEventCompat.getY(event, 1);
                    distance = (float) Math.sqrt((old_x_1 - old_x_0) * (old_x_1 - old_x_0) + (old_y_1 - old_y_0) * (old_y_1 - old_y_0));
                    break;

                case MotionEvent.ACTION_MOVE:
                    new_x_0 = MotionEventCompat.getX(event, 0);
                    new_x_1 = MotionEventCompat.getX(event, 1);
                    new_y_0 = MotionEventCompat.getY(event, 0);
                    new_y_1 = MotionEventCompat.getY(event, 1);
                    newDistance = (float) Math.sqrt((new_x_1 - new_x_0) * (new_x_1 - new_x_0) + (new_y_1 - new_y_0) * (new_y_1 - new_y_0));
                    scale = newDistance / distance;
                    imageScale(scale);
                    old_x_0=new_x_0;
                    old_x_1=new_x_1;
                    old_y_0=new_y_0;
                    old_y_1=new_y_1;
                    distance = (float) Math.sqrt((old_x_1 - old_x_0) * (old_x_1 - old_x_0) + (old_y_1 - old_y_0) * (old_y_1 - old_y_0));
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    scale = 1;
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    private void imageScale(float scale) {
        RelativeLayout.LayoutParams scalePramas = (RelativeLayout.LayoutParams) iv.getLayoutParams();
        scalePramas.width = (int) (iv.getWidth()*scale);
        scalePramas.height = (int) (iv.getHeight()*scale);
        iv.setLayoutParams(scalePramas);
    }

}
