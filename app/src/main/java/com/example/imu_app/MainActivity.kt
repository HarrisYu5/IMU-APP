package com.example.imu_app

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private val TAG = "Main"
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Message: Oncreate");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        textView = findViewById(R.id.textView)


    }


    override fun onSensorChanged(event: SensorEvent?) {
        Log.d(TAG, "Message: OnSensorChange");
        val x = event?.values?.get(0) ?: 0f
        val y = event?.values?.get(1) ?: 0f
        val z = event?.values?.get(2) ?: 0f
        // Have to use ?:0f or else wouldn't work
        textView.text = x.toString()


        val magnitude =Math.sqrt((x*x+y*y+z+z).toDouble())
        textView.text = "Acceleration Magnitude: $magnitude"
        if (magnitude < 50) {

            Toast.makeText(this, "Significant movement detected!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }
}