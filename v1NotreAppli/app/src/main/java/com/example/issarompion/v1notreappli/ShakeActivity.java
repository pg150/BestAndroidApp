package com.example.issarompion.v1notreappli;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class ShakeActivity extends AppCompatActivity implements SensorEventListener {


    private float lastX;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float deltaX = 0;
    private TextView currentX;
    public Vibrator v;
    int counter =1 ;
    boolean bool = false;
    Chronometer simpleChronometer;
    boolean all;
    long base;
    long depart;

    MediaPlayer musicW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        musicW = MediaPlayer.create(this, R.raw.win);

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
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            // fail we don't have an accelerometer!
        }
        //initialize vibration
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void initControls() {
        currentX = (TextView) findViewById(R.id.currentX);
    }
        //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
        //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the change of the x,y,z values of the accelerometer
        deltaX = Math.abs(lastX - event.values[0]);
        // if the change is below 2, it is just plain noise
        if (deltaX < 2) deltaX = 0;

        if(deltaX>9) bool = true;
        if(deltaX<4 && bool) {
            counter+=1;
            bool=false;
        }

        if(counter==4){
            simpleChronometer.stop();
            musicW.start();
            showToast("Bien joué !");
            if(all) openNextActivity();
            counter ++;
        }

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void openNextActivity(){
        long temps = (SystemClock.elapsedRealtime()-depart)/1000;
        Intent intent = new Intent(this, CricActivity.class);
        intent.putExtra("chrono",temps);
        intent.putExtra("all",true);
        intent.putExtra("depart",depart);
        startActivity(intent);
    }
}
