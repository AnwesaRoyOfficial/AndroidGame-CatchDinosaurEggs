package com.example.catcheggs1;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    Button myButtonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView wizard =(ImageView)findViewById(R.id.wizardSprite);
        wizard.setImageResource(R.drawable.attackingwizard);
        AnimationDrawable attackingWizard =(AnimationDrawable)wizard.getDrawable();
        attackingWizard.start();

        myButtonStart = (Button)findViewById(R.id.buttonStart);

        myButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(StartActivity.this,MainActivity.class);
                startActivity(i);

            }
        });

    }
}
