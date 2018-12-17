package com.example.issarompion.v1notreappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TwoPlayersActivity extends AppCompatActivity {
    private Button create;
    private Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);
        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openP2PActivity();
            }
        });
        join = (Button) findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openP2PActivity();
            }
        });
    }

    public void openP2PActivity() {
        Intent intent = new Intent(this, p2p.class);
        startActivity(intent);
    }

}