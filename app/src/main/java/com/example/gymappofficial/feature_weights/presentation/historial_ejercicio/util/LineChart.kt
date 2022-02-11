package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartUtils.calculateDrawableArea
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartUtils.calculateLinePath
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartUtils.calculatePointLocation
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartUtils.calculateRectAroundPoint
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartUtils.calculateXAxisDrawableArea
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartUtils.calculateXAxisLabelsDrawableArea
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartUtils.calculateYAxisDrawableArea
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.line.LineDrawer
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.point.FilledCircularPointDrawer
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.point.PointDrawer
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.xaxis.XAxisDrawer
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.YAxisDrawer

@Composable
fun LineChart(
    onClick: (Int) -> Unit,
    lineChartData: LineChartData,
    modifier: Modifier = Modifier,
    pointDrawer: PointDrawer = FilledCircularPointDrawer(),
    lineDrawer: LineDrawer = SolidLineDrawer(),
    xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
    yAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
    horizontalOffset: Float = 5f
) {
    val rectsMutableList = remember {
        mutableListOf<Rect>()
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { tapOffset ->
                        // When the user taps on the Canvas, you can
                        // check if the tap offset is in one of the
                        // tracked Rects.
                        var index = 0
                        var list = emptyList<Rect>()
                        list = rectsMutableList.toList()
                        for (rec in list) {
                            if (rec.contains(tapOffset)) {
                                onClick(index)
                                break // don't need to check other points,
                                // so break
                            }
                            index++
                        }
                    }
                )
            }
    )
    {
        drawIntoCanvas { canvas ->
            val yAxisDrawableArea = calculateYAxisDrawableArea(
                xAxisLabelSize = xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisDrawableArea = calculateXAxisDrawableArea(
                yAxisWidth = yAxisDrawableArea.width,
                labelHeight = xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisLabelsDrawableArea = calculateXAxisLabelsDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                offset = horizontalOffset
            )
            val chartDrawableArea = calculateDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                yAxisDrawableArea = yAxisDrawableArea,
                size = size,
                offset = horizontalOffset
            )

            // Draw the chart line.
            lineDrawer.drawLine(
                drawScope = this,
                canvas = canvas,
                linePath = calculateLinePath(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData
                )
            )

            lineChartData.points.forEachIndexed { index, point ->
                val pointOffset = calculatePointLocation(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData,
                    point = point,
                    index = index
                )
                val pointRect = calculateRectAroundPoint(pointOffset)

                pointDrawer.drawPoint(
                    drawScope = this,
                    canvas = canvas,
                    center = pointOffset
                )

                rectsMutableList.add(pointRect)
            }

            // Draw the X Axis line.
            xAxisDrawer.drawAxisLine(
                drawScope = this,
                drawableArea = xAxisDrawableArea,
                canvas = canvas
            )

            xAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                drawableArea = xAxisLabelsDrawableArea,
                labels = lineChartData.points.map { it.label }
            )

            // Draw the Y Axis line.
            yAxisDrawer.drawAxisLine(
                drawScope = this,
                canvas = canvas,
                drawableArea = yAxisDrawableArea
            )

            yAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                drawableArea = yAxisDrawableArea,
                minValue = lineChartData.minYValue,
                maxValue = lineChartData.maxYValue
            )
        }
    }
}
