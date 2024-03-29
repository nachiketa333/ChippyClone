package com.example.chippypractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

// Surface view is used when we have to change the content very quickly

public class GameEngine extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private int screenx, screenY;
    private Paint paintbrush;
    public static float screenRatioX,screenRatioY;
    private Player spaceship;
    private Background background1, background2;


    public GameEngine(Context context, int screenX, int screenY) {
        super(context);


        this.screenx = screenX;
        this.screenY = screenY;
        screenRatioX = 2560f/screenX;
        screenRatioY = 1440f/screenY;
        background1 = new Background(screenX,screenY,getResources());
        background2 = new Background(screenX,screenY,getResources());

        background2.x = screenX; // to be placed just after rhe screen ends on the x axis

        paintbrush = new Paint();

        spaceship = new Player(screenY, getResources());


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

        background1.x -= 20 * screenRatioX;
        background2.x -= 20 * screenRatioX;  // move towards x axis by 10

        if (background1.x + background1.background.getWidth() < 0)    //if its less the zero then its completely off the screen
        {
            background1.x = screenx;
        }

        if (background2.x + background2.background.getWidth() < 0)
        {
            background2.x = screenx;
        }

        if (spaceship.isgoingUp)
        {
            spaceship.y -= 30 *  screenRatioY;
        }
        else
        {
            spaceship.y += 30 * screenRatioY;
        }

        if (spaceship.y < 0) {
            spaceship.y = 0;
        }
        if (spaceship.y > screenx - spaceship.height) {
            spaceship.y = screenY - spaceship.height;
        }


    }
    public void draw()
    {
        if(getHolder().getSurface().isValid())
        {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background,background1.x,background1.y,paintbrush);
            canvas.drawBitmap(background2.background,background2.x,background2.y,paintbrush);
            canvas.drawBitmap(spaceship.spaceShip1,spaceship.x,spaceship.y,paintbrush);

            getHolder().unlockCanvasAndPost(canvas);
        }
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                    if (event.getX() < screenx / 2){
                        spaceship.isgoingUp = true;
                    }
                break;
            case MotionEvent.ACTION_UP:
                    spaceship.isgoingUp = false;
                break;
        }

        return true;
    }
}
