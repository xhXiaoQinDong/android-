package com.example.lenovo.a06266;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class dong extends Service {
    private List<Song> list;


    int i=0;
    private static final String TAG = "dong";
    public static MediaPlayer mp;
    public dong() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "onBind:");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreat:");
       /* mp.list.get(i).path;*/
        /*mp=MediaPlayer.create(this,R.raw.mingyue);*/
//        final Intent intent;

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy:");
        super.onDestroy();
        mp.release();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(i==0) {

            String path1 = intent.getStringExtra("chuandi1");
//          Toast.makeText(this, path1, Toast.LENGTH_SHORT).show();
           mp= MediaPlayer.create(this, Uri.parse(path1));
        }
        i++;

        Log.d(TAG, "onStartCommand:");
//        String path=intent.getExtras("path1");
        int op = intent.getIntExtra("op", -1);

        switch (op) {
            case Main2Activity.PLAY:
               if (!mp.isPlaying())
                    mp.start();
                /*startActivity(intent1);*/
                break;
            case Main2Activity.PAUSE:
                if (mp.isPlaying())
                    mp.pause();
                /*startActivity(intent1);*/
                break;
            case Main2Activity.xs:


//                try {
//                    mp.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                break;
            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

//    private void play(String path){
//        //播放之前要把音频文件重置
//
//        /*Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//        intent.putExtra("name","Amy");
//        intent.putExtra("age","12");
//        intent.putExtra("boy","true");
//        intent.putExtra("weizhi",playPosition);
//        intent.putExtra("dizhi","list.get(i).path");
//        startActivity(intent);*/
//        try {
//            mp.reset();
//            //调用方法传进去要播放的音频路径
//            mp.setDataSource(path);
//            //异步准备音频资源
//            mp.prepareAsync();
//            //调用mediaPlayer的监听方法，音频准备完毕会相应此方法
//            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    mediaPlayer.start();//开始音频
//                }
//            });
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

}
