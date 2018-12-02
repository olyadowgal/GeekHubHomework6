package com.example.android.homework6

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.homework6.entities.ForecastResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var url =
            "http://api.openweathermap.org/data/2.5/forecast?q=Kiev&mode=json&APPID=5a5090b563dd7bf6c7f87a629207bb31&units=metric"
        WeatherLoader(url).execute()
    }

    inner class WeatherLoader(url: String) : AsyncTask<Unit, Unit, Unit>() {

        var curr_url = url

        override fun doInBackground(vararg params: Unit) {
            val request = Request.Builder().url(curr_url).build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    val body = response?.body()?.string()

                    val gson = GsonBuilder().create()

                    val forecast = gson.fromJson(body, ForecastResponse::class.java)
                    runOnUiThread {
                        jsontext.text = forecast.toString()
                    }

                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                }
            })
        }

    }

}
