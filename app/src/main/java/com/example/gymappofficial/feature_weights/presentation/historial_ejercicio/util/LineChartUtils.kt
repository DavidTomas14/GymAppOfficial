package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

object LineChartUtils {
    fun calculateDrawableArea(
        xAxisDrawableArea: Rect,
        yAxisDrawableArea: Rect,
        size: Size,
        offset: Float
    ): Rect {
        val horizontalOffset = xAxisDrawableArea.width * offset / 100f

        return Rect(
            left = yAxisDrawableArea.right + horizontalOffset,
            top = 0f,
            bottom = xAxisDrawableArea.top,
            right = size.width - horizontalOffset
        )
    }

    fun calculateXAxisDrawableArea(
        yAxisWidth: Float,
        labelHeight: Float,
        size: Size
    ): Rect {
        val top = size.height - labelHeight

        return Rect(
            left = yAxisWidth,
            top = top,
            bottom = size.height,
            right = size.width
        )
    }

    fun calculateXAxisLabelsDrawableArea(
        xAxisDrawableArea: Rect,
        offset: Float
    ): Rect {
        val horizontalOffset = xAxisDrawableArea.width * offset / 100f

        return Rect(
            left = xAxisDrawableArea.left + horizontalOffset,
            top = xAxisDrawableArea.top,
            bottom = xAxisDrawableArea.bottom,
            right = xAxisDrawableArea.right - horizontalOffset
        )
    }

    fun Density.calculateYAxisDrawableArea(
        xAxisLabelSize: Float,
        size: Size
    ): Rect {
        // Either 50dp or 10% of the chart width.
        val right = minOf(50.dp.toPx(), size.width * 10f / 100f)

        return Rect(
            left = 0f,
            top = 0f,
            bottom = size.height - xAxisLabelSize,
            right = right
        )
    }

    fun calculatePointLocation(
        drawableArea: Rect,
        lineChartData: LineChartData,
        point: LineChartData.Point,
        index: Int
    ): Offset {
        val x = (index.toFloat() / (lineChartData.points.size - 1))
        val y = ((point.value - lineChartData.minYValue) / lineChartData.yRange)

        return Offset(
            x = (x * drawableArea.width) + drawableArea.left,
            y = drawableArea.height - (y * drawableArea.height)
        )
    }

    fun calculateRectAroundPoint(
        offset: Offset
    ): Rect {
        return Rect(
            left = offset.x * 0.90f,
            right = offset.x * 1.1f,
            top = offset.y * 0.90f,
            bottom = offset.y * 1.1f
        )
    }

    fun calculateLinePath(
        drawableArea: Rect,
        lineChartData: LineChartData
    ): Path = if (lineChartData.points.isEmpty()) Path() else
        Path().apply {
        lineChartData.points.forEachIndexed { index, point ->
            val pointLocation = calculatePointLocation(
                drawableArea = drawableArea,
                lineChartData = lineChartData,
                point = point,
                index = index
            )

            if (index == 0) {
                moveTo(pointLocation.x, pointLocation.y)
            } else {
                lineTo(pointLocation.x, pointLocation.y)
            }
        }

    }
}

