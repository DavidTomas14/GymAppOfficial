package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.line

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

interface LineDrawer {
  fun drawLine(
    drawScope: DrawScope,
    canvas: Canvas,
    linePath: Path
  )
}