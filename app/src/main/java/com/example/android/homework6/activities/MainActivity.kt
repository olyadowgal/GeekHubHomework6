package com.example.android.homework6.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.android.homework6.R
import com.example.android.homework6.activities.ThirdActivity.Companion.EXTRA_UNITS
import com.example.android.homework6.activities.ThirdActivity.Companion.REQUEST_CODE
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

    var units: String = DEFAULT_UNITS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        updateWeather()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        when (id) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_units -> {
                val sendIntent = Intent(this, ThirdActivity::class.java)
                startActivityForResult(sendIntent, REQUEST_CODE)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResult(response: ForecastResponse?) {
        response?.let {
            recyclerView_main.adapter = MainAdapter(it.list, this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE -> {
                    if (data != null) {
                        units = data.getStringExtra(EXTRA_UNITS)
                    }
                    updateWeather()

                }
            }
        }
    }

    override fun onItemClicked(item: Forecast) {
        val sendIntent = Intent(this, SecondActivity::class.java)
        sendIntent.putExtra("FORECAST", item)
        startActivity(sendIntent)
    }
    fun updateWeather() {
        WeatherLoader(
            String.format(
                BASE_URL,
                LOCATION,
                APP_ID,
                units
            ), this
        ).execute()
    }
}
