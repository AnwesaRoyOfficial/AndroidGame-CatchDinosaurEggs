package com.example.catcheggs1;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView black;
    private ImageView pink;
    private ImageView meteor1;
    private ImageView meteor2;

    private ImageView dinoRun;
    AnimationDrawable runningDino;
    private ImageView dinoJump;
    AnimationDrawable jumpimgDino;
    private ImageView dinoWalk;
    AnimationDrawable walkingDino;


    //Size
    private int frameHeight;
    private int boxSize;

    private int screenWidth;
    private int screenHeight;


    public static int countFlag=0;




    //Position
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;
    private int meteor1X;
    private int meteor1Y;
    private int meteor2X;
    private int meteor2Y;



    //Score
    private int score=0;

    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //Status Check
    private boolean action_flg = false;
    private boolean start_flg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel=(TextView)findViewById(R.id.scoreLabel);
        startLabel=(TextView)findViewById(R.id.startLabel);
        box=(ImageView)findViewById(R.id.box);
        orange=(ImageView)findViewById(R.id.orange);
        pink=(ImageView)findViewById(R.id.pink);
        black=(ImageView)findViewById(R.id.black);
        meteor1=(ImageView)findViewById(R.id.meteor1);
        meteor2=(ImageView)findViewById(R.id.meteor2);

        //------------------------------------------

        dinoRun = (ImageView)findViewById(R.id.DinoRun);
        dinoRun.setImageResource(R.drawable.dinorun);
        runningDino = (AnimationDrawable)dinoRun.getDrawable();
        runningDino.start();


        //------------------------------------------

        //------------------------------------------

        dinoJump = (ImageView)findViewById(R.id.DinoJump);
        dinoJump.setImageResource(R.drawable.dinojump);
        jumpimgDino = (AnimationDrawable)dinoJump.getDrawable();
        jumpimgDino.start();


        //------------------------------------------

        //------------------------------------------

        dinoWalk = (ImageView)findViewById(R.id.DinoWalk);
        dinoWalk.setImageResource(R.drawable.dinowalk);
        walkingDino = (AnimationDrawable)dinoWalk.getDrawable();
        walkingDino.start();


        //------------------------------------------



        //Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth=size.x;
        screenHeight=size.y;


        //Move To Out of Screen
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);
        meteor1.setX(-80);
        meteor1.setY(-80);
        meteor2.setX(-80);
        meteor2.setY(-80);

        scoreLabel.setText("Score : 0");

    }

    public void changePos()
    {







        hitCheck();

        //Orange
        orangeX -= 12;
        if(orangeX<0)
        {
            orangeX = screenWidth +20;
            orangeY = (int)Math.floor(Math.random()*(frameHeight-orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        //Black
        blackX -= 20;
        if(blackX<0)
        {
            blackX=screenWidth+10;
            blackY = (int)Math.floor(Math.random()*(frameHeight-black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);

        //Pink
        pinkX -= 35;
        if(pinkX<0)
        {
            pinkX = screenWidth+20;
            pinkY = (int)Math.floor(Math.random()*(frameHeight-pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        //Meteor1
        meteor1X -= 30;
        if(meteor1X<0)
        {
            meteor1X=screenWidth+25;
            meteor1Y=(int)Math.floor(Math.random()*(frameHeight-meteor1.getHeight()));
        }
        meteor1.setX(meteor1X);
        meteor1.setY(meteor1Y);

        //Meteor2
        meteor2X -= 30;
        if(meteor2X<0)
        {
            meteor2X=screenWidth+60;
            meteor2Y=(int)Math.floor(Math.random()*(frameHeight-meteor2.getHeight()));
        }
        meteor2.setX(meteor2X);
        meteor2.setY(meteor2Y);

        //Move Box

        if(action_flg == true)
        {

            boxY -= 15;
        }
        else
            {
              boxY += 15;
            }

        if(boxY<0)boxY =0;

        if(boxY > frameHeight-boxSize)boxY=frameHeight-boxSize;

        box.setY(boxY);

        scoreLabel.setText("Score: "+score);

    }

    public void  hitCheck()
    {



        //If the center of the ball is in the box, it counts as a hit.

        //Orange
        int orangeCenterX = orangeX + orange.getWidth()/2;
        int orangeCenterY = orangeY + orange.getHeight()/2;

        int pinkCenterX = pinkX + pink.getWidth()/2;
        int pinkCenterY = pinkY + pink.getHeight()/2;

        int blackCenterX = blackX + black.getWidth()/2;
        int blackCenterY = blackY + black.getHeight()/2;

        int meteor1CenterX = meteor1X + meteor1.getWidth();
        int meteor1CenterY = meteor1Y + meteor1.getHeight();

        int meteor2CenterX = meteor2X + meteor2.getWidth()/2;
        int meteor2CenterY = meteor2Y + meteor2.getHeight()/2;

        //0 <= orangeCenterX <= boxWidth
        //boxY <= orangeCenterY <= boxY + boxHeight

        if(0 <= orangeCenterX && orangeCenterX <= boxSize && boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize)
        {
           score += 15;
           orangeX = -20;
        }

        //Pink


        else if(0 <= pinkCenterX && pinkCenterX <= boxSize && boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize)
        {
            score += 20;
            pinkX = -20;
        }

        //Black



        else if(0 <= blackCenterX && blackCenterX <= boxSize && boxY <= blackCenterY && blackCenterY <= boxY + boxSize)
        {
            score += 10;
            blackX = -20;


        }

        else if(0<=meteor1CenterX && meteor1CenterX <= boxSize && boxY <=meteor1CenterY && meteor1CenterY <= boxY + boxSize)
        {

            //Stop Timer
            timer.cancel();
            timer=null;

            //Show Result
            Intent intent = new Intent(MainActivity.this,GameOverActivity.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);

        }

        else if(0<=meteor2CenterX && meteor2CenterX <= boxSize && boxY <=meteor2CenterY && meteor2CenterY <= boxY + boxSize)
        {

            //Stop Timer
            timer.cancel();
            timer=null;

            //Show Result
            Intent intent = new Intent(MainActivity.this,GameOverActivity.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);

        }

    }

    public boolean onTouchEvent(MotionEvent me)
    {

        if(start_flg==false)
        {
            start_flg=true;

            FrameLayout frame = (FrameLayout)findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            boxY = (int)box.getY();

            boxSize = box.getHeight();

            startLabel.setVisibility(View.GONE);

            //Call changePos() every 20 milli seconds
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            },0,100);

        }
        else
        {
            if(me.getAction()==MotionEvent.ACTION_DOWN)
            {
                action_flg=true;
            }else if(me.getAction() == MotionEvent.ACTION_UP)
            {
                action_flg = false ;
            }
        }




       return true;
    }
}
