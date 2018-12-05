package com.example.android.homework6.asynctasks

import android.os.AsyncTask
import com.example.android.homework6.entities.ForecastResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class WeatherLoader(private val url: String, private val callback: Callback) : AsyncTask<Unit, Unit, ForecastResponse>() {

    interface Callback {
        fun onResult(response: ForecastResponse?)
    }

    private val client = OkHttpClient()
    private val gson = GsonBuilder().create()

    override fun doInBackground(vararg params: Unit?): ForecastResponse? {
        val request = Request.Builder().url(url).build()
        val response: Response
        try {
            response = client.newCall(request).execute()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        val body = response.body()?.string()
        return gson.fromJson(body, ForecastResponse::class.java)
    }

    override fun onPostExecute(result: ForecastResponse?) {
        super.onPostExecute(result)
        callback.onResult(result)
    }
}