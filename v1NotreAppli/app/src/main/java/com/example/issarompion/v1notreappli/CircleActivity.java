package com.example.issarompion.v1notreappli;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.RotateAnimation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class CircleActivity extends AppCompatActivity implements RotationGestureDetector.OnRotationGestureListener {

    private RotationGestureDetector mRotationDetector;
    private ImageView ecrou;
    private float angle = 0;
    int counter =0;
    Chronometer simpleChronometer;
    boolean all;
    long base;
    long depart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

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
        ecrou = (ImageView) findViewById(R.id.ecrou);
        mRotationDetector = new RotationGestureDetector(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mRotationDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {

        counter +=1;
        Log.d("RotationGestureDetector", "Rotation: " + Float.toString(angle));
        rotate(0 - angle, 0 - rotationDetector.getAngle());
        angle = rotationDetector.getAngle();
        System.out.println(angle);
        if(counter==100){
            simpleChronometer.stop();
            showToast("Bien joué !");
            if(all) openNextActivity();
        }
    }

    private void rotate(Float angle, float degree) {
        final RotateAnimation rotateAnim = new RotateAnimation(angle, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        ecrou.startAnimation(rotateAnim);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void openNextActivity(){
        long temps = (SystemClock.elapsedRealtime()-depart)/1000;
        System.out.println(temps);
        Intent intent = new Intent(this, MouvActivity.class);
        intent.putExtra("chrono",temps);
        intent.putExtra("all",true);
        intent.putExtra("depart",depart);
        startActivity(intent);
    }

}
