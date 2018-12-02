package com.example.android.homework6.entities

import com.google.gson.annotations.SerializedName

data class Main(
    var temp: Number,
    @SerializedName("temp_min") var tempMin: Number,
    @SerializedName("temp_max") var tempMax: Number,
    var preasure : Number,
    var humidity : Int
)