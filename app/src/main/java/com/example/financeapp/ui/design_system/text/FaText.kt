package com.example.financeapp.ui.design_system.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface

@Composable
fun FaText(
    text: String,
    textType: TextType,
    textAlign: TextAlign = TextAlign.Left,
    maxLine: Int = Integer.MAX_VALUE,
    fontWeight: FontWeight? = null,
    textColor: Color? = null,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLine,
        textAlign = textAlign,
        fontWeight = fontWeight,
        style = textType.getStyle(),
        color = textColor ?: run { textType.getColor() }
    )
}

@Composable
fun FaText(
    text: AnnotatedString,
    textType: TextType,
    textAlign: TextAlign = TextAlign.Left,
    modifier: Modifier = Modifier,
    textColor: Color? = null,
    fontWeight: FontWeight? = null,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight,
        color = textColor ?: Color.Unspecified,
        style = textType.getStyle()
    )
}

@FaPreview
@Composable
fun FaTextPreview() {
    val text = LoremIpsum(10).values.first()
    PreviewSurface {

        FaText(
            text = createAnnotatedString(
                fullText = "I have agree to our Terms and Condition",
                spans = listOf(
                    SpanType.UnderLineSpan(
                        text = "Terms and Condition",
                        styles = SpanStyle(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                )
            ),
            textType = TextType.LABEL_SMALL,

            )
        FaText(text, TextType.BUTTON_LABEL)
        FaText(text, TextType.LABEL_SMALL)
        FaText(text, TextType.TITLE)
        FaText(text, TextType.LABEL_LARGE)
        FaText(text, TextType.HEADLINE_SMALL)
        FaText(text, TextType.DISPLAY_SMALL_GRADIENT)
        FaText(
            text,
            TextType.BUTTON_LABEL,
            textAlign = TextAlign.Center
        )
    }
}