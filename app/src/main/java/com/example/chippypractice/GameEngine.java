package com.example.chippypractice;

import android.content.Context;
import android.view.SurfaceView;

// Surface view is used when we have to change the content very quickly

public class GameEngine extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private Background background1, background2;

    public GameEngine(Context context) {
        super(context);
    }

    @Override
    public void run() {

        while (isPlaying)
        {
            update();
            draw();
            sleep();
        }



    }
    public void update()
    {

    }
    public void draw()
    {

    }
    public void sleep ()

    {  try {
        thread.sleep(17);
    }catch (InterruptedException e)
    {
        e.printStackTrace();  // for backtracing
    }

    }
    public void resume ()

    {
        isPlaying = true;
        thread = new Thread(this); // initialize the thread object
        thread.start();   // will call the run method when game is resumed
    }

    public void pause ()
    {
        try
        {
            isPlaying = false;
            thread.join();          // To stop the thread
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
