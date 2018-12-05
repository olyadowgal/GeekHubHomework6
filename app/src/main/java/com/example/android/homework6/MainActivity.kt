package com.example.android.homework6

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.android.homework6.adapters.MainAdapter
import com.example.android.homework6.asynctasks.WeatherLoader
import com.example.android.homework6.entities.Forecast
import com.example.android.homework6.entities.ForecastResponse
import com.example.android.homework6.interfaces.IOnItemClicked
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IOnItemClicked<Forecast>, WeatherLoader.Callback {

    companion object {

        const val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=%s&mode=json&APPID=%s&units=%s"
        const val APP_ID = "5a5090b563dd7bf6c7f87a629207bb31"
        const val LOCATION = "Kiev"
        const val DEFAULT_UNITS = "metric"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        WeatherLoader(String.format(BASE_URL, LOCATION, APP_ID, DEFAULT_UNITS), this).execute()
    }

    override fun onResult(response: ForecastResponse?) {
        response?.let {
            recyclerView_main.adapter = MainAdapter(it.list, this)
        }
    }

    override fun onItemClicked(item: Forecast) {
        val sendIntent = Intent(this, SecondActivity::class.java)
        sendIntent.putExtra("FORECAST", item)
        startActivity(sendIntent)
    }
}
