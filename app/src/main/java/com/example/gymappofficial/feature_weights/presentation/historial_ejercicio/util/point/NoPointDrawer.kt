package com.github.tehras.charts.line.renderer.point

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.point.PointDrawer

object NoPointDrawer : PointDrawer {
  override fun drawPoint(
    drawScope: DrawScope,
    canvas: Canvas,
    center: Offset
  ) {
    // Leave empty on purpose, we do not want to draw anything.
  }
}