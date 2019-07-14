package com.example.lenovo.a06266;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int playPosition;//当前播放歌曲的序号
    private boolean IsPlay = false;//是否有歌曲在播放
    private Button playPause;//暂停和播放按钮




    public static MediaPlayer mediaPlayer;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;









  /*  String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    List mPermissionList = new ArrayList<>();
    int mRequestCode = 100;*/

    public ListView mListView;
    public List<Song> list;
    public MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
       /* if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
            initPermission();
        } else {
            initView();
        }*/


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
            initView();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }

    }



    /**
     * 初始化view
     */


    public void initView() {
        mediaPlayer=new MediaPlayer();

        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.main_listview);
        list = new ArrayList<>();
        //把扫描到的音乐赋值给list
        list = MusicUtils.getMusicData(this);
        adapter = new MyAdapter(this,list);
        mListView.setAdapter((ListAdapter) adapter);




        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //创建一个播放音频的方法，把点击到的地址传过去
                //list.get(i).path就是这个歌曲的地址
//                play(list.get(i).path);
                //发送当前所点击音乐所在的位置

                String ps;
                ps=list.get(i).path;
//                Intent intent3=new Intent(MainActivity.this,dong.class);
//                intent3.putExtra("chuandi",ps);
//                startService(intent3);
//
//
//                Intent intent1=new Intent(MainActivity.this,dong.class);
//                intent1.putExtra("chuandi",ps);

                playPosition=i;
                IsPlay=true;
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("chuandi",ps);
                intent.putExtra("name","Amy");
                intent.putExtra("age","12");
                intent.putExtra("boy","true");
                intent.putExtra("weizhi",playPosition);

                startActivity(intent);
            }
        });

    }


    public  static void play(String path){
        //播放之前要把音频文件重置

        /*Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("name","Amy");
        intent.putExtra("age","12");
        intent.putExtra("boy","true");
        intent.putExtra("weizhi",playPosition);
        intent.putExtra("dizhi","list.get(i).path");
        startActivity(intent);*/
        try {
            mediaPlayer.reset();
            //调用方法传进去要播放的音频路径
            mediaPlayer.setDataSource(path);
            //异步准备音频资源
            mediaPlayer.prepareAsync();
            //调用mediaPlayer的监听方法，音频准备完毕会相应此方法
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();//开始音频
                }
            });

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    }


