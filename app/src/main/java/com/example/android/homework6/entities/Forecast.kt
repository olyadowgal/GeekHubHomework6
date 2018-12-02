package com.example.android.homework6.entities

import com.google.gson.annotations.SerializedName

data class Forecast(
    var dt: Long,
    var main: Main,
    var weather: List<Weather>,
    @SerializedName("dt_txt") var dtTxt : String
)