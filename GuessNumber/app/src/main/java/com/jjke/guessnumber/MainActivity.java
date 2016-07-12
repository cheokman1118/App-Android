package com.jjke.guessnumber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int Number;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Number = (int) (Math.random() * 100 + 1);
        tv = (TextView) findViewById(R.id.optext);

        findViewById(R.id.btsub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.editText);
                String textFromET = et.getText().toString();
                if (TextUtils.isEmpty(textFromET)) {
                    Toast.makeText(getApplicationContext(),"Please Type in a number",Toast.LENGTH_LONG).show();
                } else {
                    int input = new Integer(textFromET).intValue();
                    if (input == Number) {
                        tv.setText("Correct");
                    } else if (input > Number) {
                        tv.setText("Too Big");
                    } else {
                        tv.setText("Too Small");
                    }
                }
            }
        });
        findViewById(R.id.btnn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
