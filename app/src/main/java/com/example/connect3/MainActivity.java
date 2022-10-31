package com.example.connect3;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.setScaleX(0f);
        counter.setScaleY(0f);
        counter.setImageResource(R.drawable.yellow);
        counter.animate().scaleX(1f).scaleY(1f).setDuration(300);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}