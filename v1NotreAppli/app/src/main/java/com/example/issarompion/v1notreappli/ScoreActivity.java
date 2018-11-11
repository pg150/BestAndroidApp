package com.example.issarompion.v1notreappli;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private Button restart ;
    private Button change ;
    private Button menu ;

    long depart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        depart = getIntent().getLongExtra("depart",SystemClock.elapsedRealtime());
        long temps = (SystemClock.elapsedRealtime()-depart)/1000;
        TextView score = (TextView)findViewById(R.id.score);
        score.setText(""+ temps + "s");


        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllActivity();
            }
        });
        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOneplayerActivity();
            }
        });

        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }


    public void openAllActivity(){
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("all",true);
        startActivity(intent);
    }
    public void openOneplayerActivity(){
        Intent intent = new Intent(this, OnePlayerActivity.class);
        startActivity(intent);
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
