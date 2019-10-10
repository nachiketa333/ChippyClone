package com.example.chippypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameEngine = new GameEngine(this,point.x,point.y);

        setContentView(gameEngine);

    }

    @Override

    protected void onPause()
    {
        super.onPause();
        gameEngine.pause();
    }

    @Override

    protected void onResume()
    {
        super.onResume();
        gameEngine.resume();
    }
}

