package com.example.gymappofficial.core.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.dateFormatter(type: typeFormat): String {
    val pattern = when (type) {
        typeFormat.ALL -> "HH:mm  dd-MM-yy"
        typeFormat.DAY -> "dd"
        typeFormat.DAY_MONTH-> "dd-MM"
    }
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(this)
}

enum class typeFormat {
    ALL,
    DAY_MONTH,
    DAY
}