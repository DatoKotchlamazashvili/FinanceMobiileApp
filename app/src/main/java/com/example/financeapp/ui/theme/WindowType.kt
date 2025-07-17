package com.example.financeapp.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

sealed interface WindowType {
    object Compact : WindowType
    object Medium : WindowType
    object Expanded : WindowType
}

@Composable
fun rememberWindowSize(): WindowType {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> WindowType.Compact
        configuration.screenWidthDp < 840 -> WindowType.Medium
        else -> WindowType.Expanded
    }
}

val LocalWindowType = compositionLocalOf<WindowType> { error("WindowType not provided") }
