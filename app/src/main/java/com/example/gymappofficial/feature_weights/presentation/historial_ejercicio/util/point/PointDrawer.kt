package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.point

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

interface PointDrawer {
  fun drawPoint(
    drawScope: DrawScope,
    canvas: Canvas,
    center: Offset
  )


}
