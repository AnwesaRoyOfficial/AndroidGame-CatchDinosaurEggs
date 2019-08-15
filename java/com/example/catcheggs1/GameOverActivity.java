package com.example.catcheggs1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView txScoreLabel,txHighScoreLabel;
    Button btnTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        txScoreLabel=(TextView)findViewById(R.id.txtscoreLabel);
        txHighScoreLabel=(TextView)findViewById(R.id.txtHighScoreLabel);
        btnTryAgain=(Button)findViewById(R.id.btnTryAgain);


        int score = getIntent().getIntExtra("SCORE",0);


        txScoreLabel.setText(score+"");


        SharedPreferences settings=getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);

        if(score>highScore)
        {
            txHighScoreLabel.setText("High Score: "+score);

            //Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();


        }

        else
        {

            txHighScoreLabel.setText("High Score: "+highScore);


        }




        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(GameOverActivity.this,MainActivity.class);

                startActivity(intent);


            }
        });



    }



}
