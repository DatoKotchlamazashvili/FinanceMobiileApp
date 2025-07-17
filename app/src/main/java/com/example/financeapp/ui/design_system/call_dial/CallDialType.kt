package com.example.financeapp.ui.design_system.call_dial

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface CallDialType {
    data class Number(val number: Int) : CallDialType

    data class Icon(val iconRes: ImageVector, val callIconType: CallIconType) : CallDialType

    data object Empty : CallDialType
}


enum class CallIconType {
    DELETE
}