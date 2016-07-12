package com.jjke.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btplus, btmulti, btminus, btdiv, btres, btclear, btdot;
    private TextView tv;
    private String output = "";
    private String mode = "";
    private double lastnumber = 0;
    private int intoutput = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);
        bt5 = (Button) findViewById(R.id.button5);
        bt6 = (Button) findViewById(R.id.button6);
        bt7 = (Button) findViewById(R.id.button7);
        bt8 = (Button) findViewById(R.id.button8);
        bt9 = (Button) findViewById(R.id.button9);
        bt0 = (Button) findViewById(R.id.button10);
        btdot = (Button) findViewById(R.id.button11);
        btplus = (Button) findViewById(R.id.button12);
        btminus = (Button) findViewById(R.id.button13);
        btmulti = (Button) findViewById(R.id.button14);
        btdiv = (Button) findViewById(R.id.button15);
        btres = (Button) findViewById(R.id.button16);
        btclear = (Button) findViewById(R.id.button17);
        tv = (TextView) findViewById(R.id.textView);

        btclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output = "";
                tv.setText("0");
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt1.getText();
                tv.setText(output);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt2.getText();
                tv.setText(output);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt3.getText();
                tv.setText(output);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt4.getText();
                tv.setText(output);
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt5.getText();
                tv.setText(output);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt6.getText();
                tv.setText(output);
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt7.getText();
                tv.setText(output);
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt8.getText();
                tv.setText(output);
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output += bt9.getText();
                tv.setText(output);
            }
        });

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv.getText() != "0") {
                    output += bt0.getText();
                    tv.setText(output);
                }
            }
        });

        btdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String founddot = (String) tv.getText();
                if (founddot.indexOf(".") < 0 && output.length() > 0) {
                    output += ".";
                    tv.setText(output);
                }
                if (founddot == "0") {
                    output = "0.";
                    tv.setText(output);
                }
            }
        });

        btplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == "") {
                    mode = "+";
                    lastnumber = Double.parseDouble((String) tv.getText());
                    output = "";
                    tv.setText("0");
                }
            }
        });

        btminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == "") {
                    mode = "-";
                    lastnumber = Double.parseDouble((String) tv.getText());
                    output = "";
                    tv.setText("0");
                }
            }
        });

        btmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == "") {
                    mode = "*";
                    lastnumber = Double.parseDouble((String) tv.getText());
                    output = "";
                    tv.setText("0");
                }
            }
        });

        btdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == "") {
                    mode = "/";
                    lastnumber = Double.parseDouble((String) tv.getText());
                    output = "";
                    tv.setText("0");
                }
            }
        });

        btres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == "+") {
                    mode = "";
                    lastnumber += Double.parseDouble((String) tv.getText());

                    if ((lastnumber == Math.floor(lastnumber)) && !Double.isInfinite(lastnumber)) {
                        intoutput = (int) lastnumber;
                        output = Integer.toString(intoutput);
                        tv.setText(output);
                    } else {
                        output = Double.toString(lastnumber);
                        tv.setText(output);
                    }
                }

                if (mode == "-") {
                    mode = "";
                    lastnumber -= Double.parseDouble((String) tv.getText());

                    if ((lastnumber == Math.floor(lastnumber)) && !Double.isInfinite(lastnumber)) {
                        intoutput = (int) lastnumber;
                        output = Integer.toString(intoutput);
                        tv.setText(output);
                    } else {
                        output = Double.toString(lastnumber);
                        tv.setText(output);
                    }
                }

                if (mode == "*") {
                    mode = "";
                    lastnumber *= Double.parseDouble((String) tv.getText());

                    if ((lastnumber == Math.floor(lastnumber)) && !Double.isInfinite(lastnumber)) {
                        intoutput = (int) lastnumber;
                        output = Integer.toString(intoutput);
                        tv.setText(output);
                    } else {
                        output = Double.toString(lastnumber);
                        tv.setText(output);
                    }
                }

                if (mode == "/") {
                    mode = "";
                    lastnumber /= Double.parseDouble((String) tv.getText());

                    if ((lastnumber == Math.floor(lastnumber)) && !Double.isInfinite(lastnumber)) {
                        intoutput = (int) lastnumber;
                        output = Integer.toString(intoutput);
                        tv.setText(output);
                    } else {
                        output = Double.toString(lastnumber);
                        tv.setText(output);
                    }
                }


            }
        });


    }
}
