package com.example.android.homework6.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.homework6.R
import com.example.android.homework6.entities.Forecast

class MainAdapter(val listOfForecasts: List<Forecast>) : RecyclerView.Adapter<MainAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ForecastViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.short_forecast,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfForecasts.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.onBind(
            listOfForecasts[position].dtTxt,
            listOfForecasts[position].main.temp,
            listOfForecasts[position].weather[0].description
        )
    }

    class ForecastViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val date_text: TextView = view.findViewById(R.id.forecast_date)
        private val temp_text: TextView = view.findViewById(R.id.forecast_temp)
        private val descr_text: TextView = view.findViewById(R.id.forecast_description)

        fun onBind(date: String, temperature: Number, description: String) {
            date_text.text = date
            temp_text.text = temperature.toString()
            descr_text.text = description
        }


    }

}