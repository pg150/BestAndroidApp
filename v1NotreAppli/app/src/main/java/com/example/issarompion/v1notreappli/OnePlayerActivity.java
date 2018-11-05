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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        answer = (Button) findViewById(R.id.button4);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnswerActivity();
            }
        });
        circle = (Button) findViewById(R.id.button5);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCircleActivity();
            }
        });
        shake = (Button) findViewById(R.id.button6);
        shake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShakeActivity();
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
}
