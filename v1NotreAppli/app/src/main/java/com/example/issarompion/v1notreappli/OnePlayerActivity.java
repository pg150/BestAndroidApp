package com.example.issarompion.v1notreappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnePlayerActivity extends AppCompatActivity {
    private Button answer;
    private Button circle;
    private Button shake;
    private Button mouv;
    private Button quiz;
    private Button cric;
    private Button all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        answer = (Button) findViewById(R.id.answer);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnswerActivity();
            }
        });
        circle = (Button) findViewById(R.id.circle);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCircleActivity();
            }
        });
        shake = (Button) findViewById(R.id.shake);
        shake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShakeActivity();
            }
        });
        mouv = (Button) findViewById(R.id.mouv);
        mouv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMouvActivity();
            }
        });
        quiz = (Button) findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuizActivity();
            }
        });
        cric = (Button) findViewById(R.id.cric);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCricActivity();
            }
        });
        all = (Button) findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllActivity();
            }
        });
    }
    public void openAnswerActivity(){
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
    }
    public void openCircleActivity(){
        Intent intent = new Intent(this, CircleActivity.class);
        startActivity(intent);
    }
    public void openShakeActivity(){
        Intent intent = new Intent(this, ShakeActivity.class);
        startActivity(intent);
    }
    public void openMouvActivity(){
        Intent intent = new Intent(this, MouvActivity.class);
        startActivity(intent);
    }
    public void openQuizActivity(){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
    public void openCricActivity(){
        Intent intent = new Intent(this, CricActivity.class);
        startActivity(intent);
    }
    public void openAllActivity(){
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("all",true);
        startActivity(intent);
    }
}
