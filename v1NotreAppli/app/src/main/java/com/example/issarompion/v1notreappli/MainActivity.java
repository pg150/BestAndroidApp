package com.example.issarompion.v1notreappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  private Button oneplayer;
  private Button twoplayers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oneplayer = (Button) findViewById(R.id.button);
        oneplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnePlayerActivity();
            }
        });
        twoplayers = (Button) findViewById(R.id.button2);
        twoplayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwoPlayersActivity();
            }
        });
    }

    public void openOnePlayerActivity(){
        Intent intent = new Intent(this, OnePlayerActivity.class);
        startActivity(intent);
    }
    public void openTwoPlayersActivity(){
        Intent intent = new Intent(this, TwoPlayersActivity.class);
        startActivity(intent);
    }
}
