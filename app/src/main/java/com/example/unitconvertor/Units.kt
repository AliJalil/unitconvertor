package com.example.unitconvertor

class Metric (
    var metricName : String = "",
    var metricUnits: ArrayList<Unit> = ArrayList<Unit>()
)

class Unit(
    var unitName  : String,
    var unitFactor : Double
)