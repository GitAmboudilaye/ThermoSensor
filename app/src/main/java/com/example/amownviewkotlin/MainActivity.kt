package com.example.amownviewkotlin

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var manager: SensorManager
    private lateinit var temp: Sensor
    private lateinit var themo: Thermometre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.temp = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        manager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL)

        themo = Thermometre(this, 200f)
        setContentView(themo)



    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (sensorEvent.values[0] >= 0) {
            themo.setTempAffiche(sensorEvent.values[0])
            themo.setDynamicColor(sensorEvent.values[0])
        } else {
            themo.setTempAffiche(0.0f)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}