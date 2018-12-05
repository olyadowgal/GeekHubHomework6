package com.example.android.homework6.entities

import java.io.Serializable

data class ForecastResponse (val list : List<Forecast>, val city : City) : Serializable