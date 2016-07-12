package com.jjke.reminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private SimpleCursorAdapter adapter;
    private Db db;
    private SQLiteDatabase dbRead,dbWrite;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        startService(new Intent(this,MyService.class));

        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        adapter = new SimpleCursorAdapter(this,R.layout.list_cell,null,new String[]{"time","even"},new int[]{R.id.tvTime,R.id.tvEven});
       lv.setAdapter(adapter);
       refreshView();

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this).setTitle("你确定要刪除這項?").setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cursor c = adapter.getCursor();
                        c.moveToPosition(position);
                        int itemId = c.getInt(c.getColumnIndex("_id"));
                        dbWrite.delete("fact","_id=?",new String[]{itemId+""});
                        refreshView();
                        startService(new Intent(MainActivity.this,MyService.class));
                    }
                }).setNegativeButton("取消",null).show();
                return true;
            }
        });

    }

    private void refreshView() {
        Cursor c = dbRead.query("fact",null,null,null,null,null,null);
        adapter.changeCursor(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Add:
                Intent intent = new Intent(MainActivity.this,Input.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshView();
    }
}
