package com.jike.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ImageView imageView;
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float predegree = 0;
    private int output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.degree);
        imageView = (ImageView) findViewById(R.id.ivCompass);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = event.values[0];
        RotateAnimation animation = new RotateAnimation(predegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);
        imageView.startAnimation(animation);
        predegree = -degree;
        output = (int) event.values[0];
        if (isBetween(output, 0, 90)) {
            textView.setText(String.format("N %dº E", output));
        } else if (isBetween(output, 91, 180)) {
            textView.setText(String.format("S %dº E", 180 - output));
        } else if (isBetween(output, 181, 270)) {
            textView.setText(String.format("S %dº W", output - 180));
        } else {
            textView.setText(String.format("N %dº W", 360 - output));
        }
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return x >= lower && x <= upper;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //TODO
    }
}
