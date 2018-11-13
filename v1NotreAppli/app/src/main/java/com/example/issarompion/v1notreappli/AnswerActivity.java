package com.example.issarompion.v1notreappli;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import android.media.*;

public class AnswerActivity extends AppCompatActivity {
    int value1;
    int value2;
    int op;
    Chronometer simpleChronometer;
    boolean all;
    long depart;

    MediaPlayer musicW;
    MediaPlayer musicL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        musicW = MediaPlayer.create(this, R.raw.win);
        musicL = MediaPlayer.create(this, R.raw.lose);


        all = getIntent().getBooleanExtra("all",false);
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        depart = SystemClock.elapsedRealtime();
        simpleChronometer.setBase(depart);
        simpleChronometer.start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        value1 = getRandomNumber(0,20);
        value2 = getRandomNumber(0,value1);
        op = getRandomNumber(0,1);

        if(op==0){
            TextView Number1 = findViewById(R.id.operation);
            Number1.setText("" + value1+ "  vis  + " + value2+" vis = ?");
        }
        else{
            TextView Number1 = findViewById(R.id.operation);
            Number1.setText("" + value1+ " vis  - " + value2+" vis = ?");
        }
    }

    public void onSubmitClick (View view){

        EditText Attempt = findViewById(R.id.reponse);
        int userAnswer = 41;
        if( isNumeric(Attempt.getText().toString())) {
            userAnswer = Integer.parseInt(Attempt.getText().toString());
        }
        if (op==0){
            if(userAnswer == value1+value2) {
                simpleChronometer.stop();
                musicW.start();
                showToast("Bonne réponse !");
                if(all) openNextActivity();



            } else {
                showToast("Mauvaise réponse. Recommencez !");
                musicL.start();
            }
        }
        else{
            if(userAnswer == value1-value2) {
                simpleChronometer.stop();
                musicW.start();
                showToast("Bonne réponse !");
                if(all) openNextActivity();

            } else {
                showToast("Mauvaise réponse. Recommencez !");
                musicL.start();
            }
        }


    }
    private static int getRandomNumber(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public void openNextActivity(){
        long temps = (SystemClock.elapsedRealtime()-depart)/1000;
        System.out.println(temps);
        Intent intent = new Intent(this, CircleActivity.class);
        intent.putExtra("chrono",temps);
        intent.putExtra("all",true);
        intent.putExtra("depart",depart);
        startActivity(intent);
    }
}
