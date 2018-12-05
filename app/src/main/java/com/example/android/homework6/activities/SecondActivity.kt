package com.example.android.homework6.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.homework6.R
import com.example.android.homework6.entities.Forecast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    companion object {
        lateinit var forecast : Forecast
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (intent.hasExtra("FORECAST")) {
            forecast = intent.getSerializableExtra("FORECAST") as Forecast
            addText()
        }
    }

    private fun addText() {
        txt_temp.text = forecast.main.temp.toString()
        txt_max_temp.text = forecast.main.tempMax.toString()
        txt_min_temp.text = forecast.main.tempMin.toString()
        txt_description.text = forecast.weather[0].description
    }
}

