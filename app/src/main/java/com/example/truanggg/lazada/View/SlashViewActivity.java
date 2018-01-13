package com.example.truanggg.lazada.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.truanggg.lazada.R;

public class SlashViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_view);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);//màn
                }catch (Exception e){

                }finally {
                    Intent iTrangChu = new Intent(SlashViewActivity.this,TrangChuActivity.class);
                    startActivity(iTrangChu);
                }
            }
        });
        thread.start(); // chạy slashview
    }
}
