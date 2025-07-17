package com.example.financeapp.ui.design_system.text


import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.theme.LocalDimensions

@Composable
fun PinInputField(
    pin: String,
    onPinChange: (String) -> Unit,
    length: Int = 4,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .focusable()
    ) {
        BasicTextField(
            value = pin,
            onValueChange = { new ->
                val filtered = new.filter { it.isDigit() }.take(length)
                onPinChange(filtered)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            cursorBrush = SolidColor(Color.Blue),
            textStyle = LocalTextStyle.current.copy(color = Color.Transparent),
            modifier = Modifier
                .matchParentSize()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions16),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            repeat(length) { idx ->
                val char = pin.getOrNull(idx)?.toString() ?: " "
                val isFilled = idx < pin.length
                val isActive = idx == pin.length

                val underlineColor = when {
                    isActive -> MaterialTheme.colorScheme.primary
                    isFilled -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                }
                val textColor = when {
                    isActive -> MaterialTheme.colorScheme.primary
                    isFilled -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = char,
                        fontSize = 36.sp,
                        color = textColor
                    )
                    Spacer(Modifier.height(LocalDimensions.current.dimensions8))
                    Box(
                        Modifier
                            .width(LocalDimensions.current.dimensions32)
                            .height(LocalDimensions.current.dimensions2)
                            .background(underlineColor)
                    )
                }
            }
        }
    }
}


@FaPreview
@Composable
fun DemoPinScreen() {
    PreviewSurface {
        var pin by remember { mutableStateOf("1") }

        PinInputField(
            pin = pin,
            onPinChange = { pin = it },
            length = 4,
            modifier = Modifier.padding(32.dp)
        )
    }
}
