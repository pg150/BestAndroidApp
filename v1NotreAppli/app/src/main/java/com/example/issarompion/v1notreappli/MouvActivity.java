package com.example.issarompion.v1notreappli;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MouvActivity extends AppCompatActivity {

    private ImageView ecusson;
    private ImageView etoile;
    Chronometer simpleChronometer;
    boolean all;
    long base;
    long depart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouv);

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
        initControls();

    }

    private void initControls() {
        ecusson = (ImageView) findViewById(R.id.ecusson);
        etoile = (ImageView) findViewById(R.id.etoile);
        etoile.setOnTouchListener(new MyTouchListener());
        ecusson.setOnDragListener(new MyDragListener());
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }


    private class MyDragListener implements View.OnDragListener {
        int resAct = R.drawable.edf2etoiles;
        int resNormal = R.drawable.edf1etoile;


        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    ((ImageView) v).setImageResource(resAct);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    ((ImageView) v).setImageResource(resNormal);
                    break;
                case DragEvent.ACTION_DROP:
                    ((ImageView) v).setImageResource(resAct);
                    // Display toast
                    simpleChronometer.stop();
                    showToast("Bien joué vous êtes champion du monde");
                    if(all) openNextActivity();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (event.getResult()) { // drop succeeded
                        ((ImageView) v).setImageResource(resAct);
                    } else { // drop failed
                        final View draggedView = (View) event.getLocalState();
                        draggedView.post(new Runnable() {
                            @Override
                            public void run() {
                                draggedView.setVisibility(View.VISIBLE);
                            }
                        });
                        ((ImageView) v).setImageResource(resNormal);
                    }
                default:
                    break;
            }
            return true;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void openNextActivity(){
        long temps = (SystemClock.elapsedRealtime()-depart)/1000;
        Intent intent = new Intent(this, ShakeActivity.class);
        intent.putExtra("chrono",temps);
        intent.putExtra("all",true);
        intent.putExtra("depart",depart);
        startActivity(intent);
    }

}

