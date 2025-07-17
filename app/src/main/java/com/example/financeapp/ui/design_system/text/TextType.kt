package com.example.financeapp.ui.design_system.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

enum class TextType {
    DISPLAY_SMALL_GRADIENT,
    DISPLAY_MEDIUM,
    DISPLAY_MEDIUM_INCOME,
    DISPLAY_MEDIUM_EXPENSE,
    HEADLINE_SMALL,
    TITLE,
    LABEL_SMALL,
    LABEL_LARGE,
    LABEL_LARGE_VARIANT,
    LABEL_LARGE_PRIMARY,
    BUTTON_LABEL,
    BUTTON_INACTIVE
}

@Composable
fun TextType.getColor() =
    when (this) {
        TextType.LABEL_SMALL, TextType.LABEL_LARGE_VARIANT -> MaterialTheme.colorScheme.onSurfaceVariant
        TextType.LABEL_LARGE -> MaterialTheme.colorScheme.onSurface
        TextType.TITLE -> MaterialTheme.colorScheme.onSurface
        TextType.HEADLINE_SMALL -> MaterialTheme.colorScheme.primary
        TextType.DISPLAY_SMALL_GRADIENT -> MaterialTheme.colorScheme.onSurface
        TextType.BUTTON_LABEL -> MaterialTheme.colorScheme.onPrimary
        TextType.DISPLAY_MEDIUM -> MaterialTheme.colorScheme.onSurface
        TextType.DISPLAY_MEDIUM_INCOME -> Color.Yellow
        TextType.DISPLAY_MEDIUM_EXPENSE -> Color.Blue
        TextType.LABEL_LARGE_PRIMARY -> MaterialTheme.colorScheme.primary
        TextType.BUTTON_INACTIVE -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    }

@Composable
fun TextType.getStyle() =
    when (this) {
        TextType.LABEL_SMALL -> MaterialTheme.typography.labelSmall
        TextType.LABEL_LARGE, TextType.LABEL_LARGE_VARIANT -> MaterialTheme.typography.labelLarge
        TextType.LABEL_LARGE_PRIMARY -> MaterialTheme.typography.labelLarge
        TextType.TITLE -> MaterialTheme.typography.titleMedium
        TextType.HEADLINE_SMALL -> MaterialTheme.typography.headlineSmall
        TextType.DISPLAY_SMALL_GRADIENT -> MaterialTheme.typography.displaySmall.copy(
            brush = Brush.linearGradient(
                listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
            )
        )

        TextType.BUTTON_LABEL -> MaterialTheme.typography.labelLarge
        TextType.DISPLAY_MEDIUM, TextType.DISPLAY_MEDIUM_EXPENSE, TextType.DISPLAY_MEDIUM_INCOME -> MaterialTheme.typography.displayMedium
        TextType.BUTTON_INACTIVE -> MaterialTheme.typography.labelLarge
    }
