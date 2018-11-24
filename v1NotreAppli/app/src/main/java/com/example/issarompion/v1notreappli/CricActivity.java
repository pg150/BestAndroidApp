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
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.issarompion.v1notreappli.R.layout.activity_cric;

public class CricActivity extends AppCompatActivity implements SensorEventListener {

    private float lastZ;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float deltaZ = 0;
    private TextView currentZ;
    public Vibrator v;
    int counter =1 ;
    boolean bool = false;
    Chronometer simpleChronometer;
    boolean all;
    long base;
    long depart;

    MediaPlayer musicC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cric);

        musicC = MediaPlayer.create(this, R.raw.soncric);


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
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null) {
            // success! we have an accelerometer
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            // fail we don't have an accelerometer!
        }
        //initialize vibration
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void initControls() {
        currentZ = (TextView) findViewById(R.id.currentZ);
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
        deltaZ = Math.abs(lastZ - event.values[2]);
        // if the change is below 2, it is just plain noise
        if (deltaZ < 2)
            deltaZ = 0;

        if(deltaZ>9) bool = true;
        if(deltaZ<4 && bool) {
            musicC.start();
            counter+=1;
            bool=false;
        }

        if(counter==3){
            simpleChronometer.stop();
            showToast("Bien joué !");
            if(all) openNextActivity();
        }

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void openNextActivity(){
        long temps = (SystemClock.elapsedRealtime()-depart)/1000;
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("chrono",temps);
        intent.putExtra("all",true);
        intent.putExtra("depart",depart);
        startActivity(intent);
    }
}