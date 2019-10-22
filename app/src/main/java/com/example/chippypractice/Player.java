package com.example.chippypractice;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.example.chippypractice.GameEngine.screenRatioX;
import static com.example.chippypractice.GameEngine.screenRatioY;

public class Player {

    int x,y,width,height;

    Bitmap spaceShip1;

    Player(int screenY, Resources res)
    {
        spaceShip1 = BitmapFactory.decodeResource(res, R.drawable.spaceship);

        width = spaceShip1.getWidth();
        height = spaceShip1.getHeight();


        width /= 8;
        height /= 8;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        spaceShip1 = Bitmap.createScaledBitmap(spaceShip1,width,height,false);

        y = screenY /2;
        x = (int) (64 * screenRatioX);
    }
}
