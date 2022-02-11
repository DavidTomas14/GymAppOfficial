package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.util.dateFormatter
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartData.Point
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.point.HollowCircularPointDrawer
import com.github.tehras.charts.line.renderer.point.NoPointDrawer
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.point.PointDrawer

class LineChartDataModel(val points: List<Point>){

    private val listOfPoints = mutableListOf<Point>()
    /*init {
        transformData()
    }*/


    val lineChartData = LineChartData(points)

    /*private fun transformData(){
        weights.sortedBy{ it.date }.forEach { weight->
            listOfPoints.add(
                Point(weight.weight, weight.date.dateFormatter())
            )
        }
    }*/
    var horizontalOffset by mutableStateOf(5f)
    var pointDrawerType by mutableStateOf(PointDrawerType.Filled)
    val pointDrawer: PointDrawer
        get() {
            return when (pointDrawerType) {
                PointDrawerType.None -> NoPointDrawer
                PointDrawerType.Filled -> FilledCircularPointDrawer()
                PointDrawerType.Hollow -> HollowCircularPointDrawer()
            }
        }

    enum class PointDrawerType {
        None,
        Filled,
        Hollow
    }
}
