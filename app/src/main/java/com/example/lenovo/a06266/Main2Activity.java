package com.example.lenovo.a06266;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ListView mListView;
    private List<Song> list;
    private MyAdapter adapter;
    int i;

    public static final int PLAY=1;
    public static final int PAUSE=2;
    public static final int xs=3;
    private MediaPlayer mp;
    String path1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.img_animation);
        LinearInterpolator lin=new LinearInterpolator();
        animation.setInterpolator(lin);
        imageView.startAnimation(animation);


        Button btnPlay=(Button) findViewById(R.id.btnPlay);
        Button btnPause=(Button)findViewById(R.id.btnPause);
        Button btnxs=(Button)findViewById(R.id.btnxs);
//        Button btnStopService=(Button)findViewById(R.id.btnStopService);

        MyOnClickListener listener=new MyOnClickListener();

        btnPlay.setOnClickListener(listener);
        btnPause.setOnClickListener(listener);
        btnxs.setOnClickListener(listener);
//        btnStopService.setOnClickListener(listener);

        Intent intent=getIntent();
//        int canshu=intent.getIntExtra("canshu",0);
//        String name=intent.getStringExtra("name");
//        int age=intent.getIntExtra("age",0);
//        int weizhi=intent.getIntExtra("weizhi",0);
        path1=intent.getStringExtra("chuandi");
//        play(path1);
        Intent intent3=new Intent(Main2Activity.this,dong.class);
//        stopService(intent3);
        intent3.putExtra("chuandi1",path1);
        intent3.putExtra("op",PLAY);
        Toast.makeText(this, path1, Toast.LENGTH_SHORT).show();
        startService(intent3);
//        Toast.makeText(this, weizhi, Toast.LENGTH_SHORT).show();

//        list = MusicUtils.getMusicData(this);

//        path1=list.get(i).path;
//        boolean isboy=intent.getBooleanExtra("boy",false);
//        String dz=intent.getStringExtra("dizhi");
//        TextView textView0=(TextView) findViewById(R.id.textView1);
//        textView0.setText("姓名 "+name+",年龄"+age+",是否男孩:"+isboy+",地址:"+dz+",参数:"+canshu+",位置:"+weizhi+",传递:"+path1);
        //path1=list.get(weizhi).path;
//        Toast.makeText(this, weizhi, Toast.LENGTH_SHORT).show();
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
//            Intent intent=new Intent(Main2Activity.this, dong.class);
            Button btn=(Button)view;
            switch (btn.getId()){
                case R.id.btnPlay:
                    Intent intent=new Intent(Main2Activity.this, dong.class);
                    intent.putExtra("op",PLAY);
                    stopService(intent);
                    intent.putExtra("chuandi1",path1);
                    startService(intent);
                    break;
                case R.id.btnPause:
                    Intent intent1=new Intent(Main2Activity.this, dong.class);
                    intent1.putExtra("op",PAUSE);
                    stopService(intent1);
                    intent1.putExtra("chuandi1",path1);
                    startService(intent1);
                    break;
                case R.id.btnxs:
                    Toast.makeText(Main2Activity.this, path1, Toast.LENGTH_SHORT).show();
                    break;


            }

        }
    }



}
