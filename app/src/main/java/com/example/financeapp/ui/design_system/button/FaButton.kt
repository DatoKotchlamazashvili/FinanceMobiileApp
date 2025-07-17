package com.example.financeapp.ui.design_system.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.theme.LocalDimensions
import com.example.financeapp.ui.theme.LocalShapes

@Composable
fun FaButton(
    modifier: Modifier = Modifier,
    text: String,
    textFontWeight: FontWeight? = null,
    onClick: () -> Unit,
    buttonType: ButtonType = ButtonType.FILLED_TONAL,
    icon: Int? = null,
    isFullWidth: Boolean = false,
    shouldWrapWidth: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = modifier.buttonWidth(isFullWidth, shouldWrapWidth),
        colors = buttonType.getButtonColor(),
        elevation = buttonType.getButtonElevation(),
        border = buttonType.getBorderStroke(),
        shape = LocalShapes.current.button
    ) {
        icon?.let {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$text button",
                modifier = Modifier.size(LocalDimensions.current.dimensions24)
            )
            Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions8))
        }
        FaText(
            text = text,
            textType = buttonType.getTextType(),
            fontWeight = textFontWeight
        )
    }
}

@FaPreview
@Composable
fun FaButtonPreview() {
    PreviewSurface {
        FaButton(text = "sdfjkldsf", onClick = {}, textFontWeight = FontWeight.SemiBold)
        FaButton(text = "sdfjkldsf", onClick = {}, isFullWidth = true)

        FaButton(
            text = "sdfjkldsf",
            onClick = {},
            textFontWeight = FontWeight.Bold,
            isFullWidth = true,
            buttonType = ButtonType.NOT_FILLED_STROKE
        )

        FaButton(
            text = "sample",
            isFullWidth = true,
            buttonType = ButtonType.TEXT,
            onClick = {}
        )

    }
}