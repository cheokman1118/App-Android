package com.jjke.reminder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Input extends AppCompatActivity {
    private EditText etTime,etEven;
    private Button btadd;
    private Db db;
    private SQLiteDatabase dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        etTime = (EditText) findViewById(R.id.etTime);
        etEven = (EditText) findViewById(R.id.etEven);
        btadd = (Button) findViewById(R.id.button);

        db = new Db(this);
        dbWrite = db.getWritableDatabase();

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("time",etTime.getText().toString());
                cv.put("even",etEven.getText().toString());
                dbWrite.insert("fact",null,cv);
                startService(new Intent(Input.this,MyService.class));
                Toast.makeText(Input.this,"添加成功",Toast.LENGTH_LONG).show();
            }
        });
    }
}
