package com.example.financeapp.ui.design_system.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.theme.LocalDimensions

enum class ButtonType {
    FILLED_TONAL,
    TEXT,
    ELEVATED,
    NOT_FILLED_STROKE,
}
fun ButtonType.getTextType() =
    when (this) {
        ButtonType.FILLED_TONAL -> TextType.BUTTON_LABEL
        ButtonType.TEXT -> TextType.LABEL_SMALL
        ButtonType.ELEVATED -> TextType.LABEL_LARGE
        ButtonType.NOT_FILLED_STROKE -> TextType.LABEL_LARGE
    }

@Composable
fun ButtonType.getButtonElevation() =
    when (this) {
        ButtonType.FILLED_TONAL -> ButtonDefaults.filledTonalButtonElevation()
        ButtonType.TEXT -> ButtonDefaults.buttonElevation()
        ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonElevation()
        ButtonType.NOT_FILLED_STROKE -> ButtonDefaults.filledTonalButtonElevation()
    }

@Composable
fun ButtonType.getButtonColor() =
    when (this) {
        ButtonType.FILLED_TONAL -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )

        ButtonType.TEXT -> ButtonDefaults.textButtonColors()
        ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonColors()
        ButtonType.NOT_FILLED_STROKE -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    }

@Composable
fun ButtonType.getBorderStroke(): BorderStroke? =
    when (this) {
        ButtonType.FILLED_TONAL -> null
        ButtonType.TEXT -> null
        ButtonType.ELEVATED -> null
        ButtonType.NOT_FILLED_STROKE -> BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }

@Composable
fun Modifier.buttonWidth(
    isFullWidth: Boolean,
    shouldWrapWidth: Boolean
) = when {
    isFullWidth -> fillMaxWidth()
    shouldWrapWidth -> wrapContentSize()
    else -> width(LocalDimensions.current.dimensions180)
}.height(LocalDimensions.current.dimensions56)
