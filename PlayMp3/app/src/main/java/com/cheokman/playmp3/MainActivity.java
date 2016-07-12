package com.cheokman.playmp3;

import android.content.Intent;
import android.media.MediaPlayer;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPlayraw,btnStop,btnSysplay,btnMediaPlayer;
    private MediaPlayer mediaPlayer;
    private String fileName = "lf.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlayraw = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnSysplay = (Button) findViewById(R.id.btnSysPlay);
        btnMediaPlayer = (Button) findViewById(R.id.btnMediaPlayer);

        btnMediaPlayer.setOnClickListener(this);
        btnPlayraw.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnSysplay.setOnClickListener(this);

        if (!fileExist(fileName)){
            copyToMobile(fileName);
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.btnPlay):
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(this,R.raw.lf);
                    mediaPlayer.start();
                }
                break;
            case R.id.btnStop:
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                break;
            case R.id.btnSysPlay:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.parse("file://"+getDir()+fileName),"audio/mp3");
                startActivity(i);
                break;
            case R.id.btnMediaPlayer:
                if (mediaPlayer == null){
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(getDir()+fileName);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    //拷貝mp3文件到手機
    private String getDir(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()+"/myplayer/";
    }
    private boolean fileExist(String fileName){
        File file = new File(getDir() + fileName);
        if(file.exists())
            return true;
        return false;
    }

    private void copyToMobile(final String fileName){
        new Thread(){
            @Override
            public void run() {
                File dir = new File(getDir());
                if(!dir.exists()){
                    dir.mkdir();
                }

                InputStream fis = null;//用于读取文件中的数据流
                OutputStream fos = null;//用于把数据写入到文件中
                //从资源文件中获取到了raw下到music.mp3到输入了，可以用来把数据读取出来
                fis = getResources().openRawResource(R.raw.lf);
                //创建一个被写入数据到文件
                File to = new File(getDir(), fileName);

                try {
                    //创建一个文件输出流来把数据写入到文件中
                    fos = new FileOutputStream(to);

                    byte[] buf = new byte[4096];
                    while(true){
                        int r = fis.read(buf);

                        if(r == -1){
                            break;
                        }
                        fos.write(buf);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }.start();

    }
}
