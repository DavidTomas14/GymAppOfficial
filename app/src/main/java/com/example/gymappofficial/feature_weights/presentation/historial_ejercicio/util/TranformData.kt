package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util

import androidx.compose.ui.Modifier
import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.util.dateFormatter
import com.example.gymappofficial.core.util.typeFormat

fun List<Weight>.transformData(): List<LineChartData.Point> {
    val listOfPoints = mutableListOf<LineChartData.Point>()
    listOfPoints.add(LineChartData.Point(0f, this[0].date.dateFormatter(typeFormat.DAY_MONTH)))
    this.sortedBy { it.date }.forEach { weight ->
        listOfPoints.add(
            LineChartData.Point(weight.weight, weight.date.dateFormatter(typeFormat.DAY_MONTH))
        )
    }
    return listOfPoints
}
