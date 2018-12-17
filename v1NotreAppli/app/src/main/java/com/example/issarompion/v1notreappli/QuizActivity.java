package com.example.issarompion.v1notreappli;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    Chronometer simpleChronometer;
    boolean all;
    long base;
    long depart;
    TextView Word;
    String answer;
    ArrayList<Outil> OutilList = new ArrayList<Outil>();

    MediaPlayer musicW;
    MediaPlayer musicL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        musicW = MediaPlayer.create(this, R.raw.win);
        musicL = MediaPlayer.create(this, R.raw.lose);


        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        all = getIntent().getBooleanExtra("all",false);
        base = getIntent().getLongExtra("chrono",0);
        depart = getIntent().getLongExtra("depart",SystemClock.elapsedRealtime());

        if(all){
            simpleChronometer.setBase(SystemClock.elapsedRealtime() - base*1000);
        }
        else simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Word = findViewById(R.id.operation);

        ArrayList<String> w1= new ArrayList<String>();w1.add("t"); w1.add("o"); w1.add("u"); w1.add("r"); w1.add("n"); w1.add("e"); w1.add("v"); w1.add("i"); w1.add("s");
        Outil o1 = new Outil("tournevis",w1); OutilList.add(o1);
        ArrayList<String> w2= new ArrayList<String>();w2.add("m"); w2.add("a"); w2.add("r"); w2.add("t"); w2.add("e"); w2.add("a"); w2.add("u");
        Outil o2 = new Outil("marteau",w2); OutilList.add(o2);
        ArrayList<String> w3= new ArrayList<String>();w3.add("p"); w3.add("e"); w3.add("r"); w3.add("c"); w3.add("e"); w3.add("u"); w3.add("s"); w3.add("e");
        Outil o3 = new Outil("perceuse",w3); OutilList.add(o3);
        ArrayList<String> w4= new ArrayList<String>();w4.add("p"); w4.add("i"); w4.add("n"); w4.add("c"); w4.add("e");
        Outil o4 = new Outil("pince",w4); OutilList.add(o4);
        ArrayList<String> w5= new ArrayList<String>();w5.add("t"); w5.add("r"); w5.add("o"); w5.add("n"); w5.add("ç"); w5.add("o"); w5.add("n"); w5.add("n");w5.add("e");w5.add("u");w5.add("s");w5.add("e");
        Outil o5 = new Outil("tronçonneuse",w5); OutilList.add(o5);
        ArrayList<String> w6= new ArrayList<String>();w6.add("c"); w6.add("r"); w6.add("i"); w6.add("c");
        Outil o6 = new Outil("cric",w6); OutilList.add(o6);
        ArrayList<String> w7= new ArrayList<String>();w7.add("m"); w7.add("e"); w7.add("u"); w7.add("l"); w7.add("e"); w7.add("u"); w7.add("s"); w7.add("e");
        Outil o7 = new Outil("meuleuse",w7); OutilList.add(o7);
        ArrayList<String> w8= new ArrayList<String>();w8.add("e"); w8.add("s"); w8.add("c"); w8.add("a"); w8.add("b"); w8.add("o"); w8.add("t");
        Outil o8 = new Outil("escabot",w8); OutilList.add(o8);
        ArrayList<String> w9= new ArrayList<String>();w9.add("p"); w9.add("o"); w9.add("l"); w9.add("i"); w9.add("s"); w9.add("s"); w9.add("e"); w9.add("u");w9.add("s");w9.add("e");
        Outil o9 = new Outil("polisseuse",w9); OutilList.add(o9);
        ArrayList<String> w10= new ArrayList<String>();w10.add("d"); w10.add("e"); w10.add("c"); w10.add("a"); w10.add("p"); w10.add("s"); w10.add("u"); w10.add("l");w10.add("e");w10.add("u");w10.add("r");
        Outil o10 = new Outil("decapsuleur",w10); OutilList.add(o10);


        int index = getRandomNumber(0,OutilList.size());
        GenerateOutil(OutilList.get(index));
    }

    public void onSubmitClick (View view){

        EditText Attempt = findViewById(R.id.reponse);

            if(Attempt.getText().toString().equals(answer)) {
                simpleChronometer.stop();
                musicW.start();
                showToast("Bonne réponse !");
                if(all) openNextActivity();

            } else {
                musicL.start();
                showToast("Mauvaise réponse. Recommencez !");
            }
        }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private static int getRandomNumber(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void GenerateOutil (Outil o){
        String res="";
        while(o.word.size()>1){
            int i = getRandomNumber(0,o.word.size()-1);
            res+=o.word.get(i);
            o.word.remove(i);
        }
        res+=o.word.get(0);
        Word.setText(res);
        answer=o.name;
    }


    public void openNextActivity(){
        long temps = (SystemClock.elapsedRealtime()-depart)/1000;
        Intent intent = new Intent(this, MouvActivity.class);
        intent.putExtra("chrono",temps);
        intent.putExtra("all",true);
        intent.putExtra("depart",depart);
        startActivity(intent);
    }


    public class Outil {
        String name;
        ArrayList<String> word;
        public Outil(String n, ArrayList<String>w){
            name=n;
            word=w;
        }
    }
}
