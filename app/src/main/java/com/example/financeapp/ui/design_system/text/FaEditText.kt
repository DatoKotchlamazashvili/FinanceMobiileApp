package com.example.financeapp.ui.design_system.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.theme.LocalDimensions

@Composable
fun FaEditField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    leadingIconRes: ImageVector? = null,
    hint: String,
    title: String,
    isSecret: Boolean = false,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val hasContent = value.isNotEmpty()
    val showBorder = isFocused || hasContent

    Column(modifier = modifier) {
        FaText(text = title, textType = TextType.LABEL_SMALL, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions12))
        OutlinedTextField(
            value = value,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF0F0D23),
                unfocusedBorderColor = if (showBorder) Color(0xFF0F0D23) else Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = if (showBorder) Color.Transparent else Color(0xFFF7F7F7),
            ),
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused },
            visualTransformation = if (isSecret && !isPasswordVisible) {
                PasswordVisualTransformation(mask = '*')
            } else VisualTransformation.None,
            placeholder = { FaText(text = hint, textType = TextType.LABEL_SMALL) },
            leadingIcon = leadingIconRes?.let { icon ->
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            trailingIcon = if (isSecret) {
                {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            } else null
        )
    }
}

@FaPreview
@Composable
private fun FaEditFieldPreview() {
    PreviewSurface {
        Column {
            (0..2).map {
                FaEditField(
                    hint = "$it value",
                    value = "",
                    isSecret = it == 2,
                    leadingIconRes = null,
                    onValueChange = {},
                    title = "asds",

                    )
            }


        }

    }
}