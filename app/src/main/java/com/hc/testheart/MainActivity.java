package com.hc.testheart;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    HeartView heartView;
    MediaPlayer mediaPlayer01;
    private FlyTxtView tv;
    private LinearLayout ll;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            // 要做的事情
            tv.setTexts("遇到你之前没想过结婚，遇到你之后结婚没想过别人这么长时间接触下来对你的爱不降反增，每时每刻都在想你，会用我的下半辈子好好疼你，爱你，宠你，陪你到天荒地老。小！雅！我！爱！你！");
            tv.startAnimation();
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heartView = (HeartView) findViewById(R.id.surfaceView);
        ll = (LinearLayout) findViewById(R.id.ll);
        tv = (FlyTxtView) findViewById(R.id.tv);
        ll.setBackgroundColor((Color.rgb(251, 255, 242)));
        mediaPlayer01 = MediaPlayer.create(MainActivity.this, R.raw.love);
        mediaPlayer01.start();
        new Thread(new MyThread()).start();


    }
    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(10000);// 线程暂停10秒，单位毫秒
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);// 发送消息
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        heartView.reDraw();
        return super.onTouchEvent(event);
    }

    public void reDraw(View v) {

        heartView.reDraw();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer01.stop();
    }
}
