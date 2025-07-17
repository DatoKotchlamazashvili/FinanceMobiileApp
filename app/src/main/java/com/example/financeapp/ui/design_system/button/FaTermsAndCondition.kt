package com.example.financeapp.ui.design_system.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.SpanType
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.design_system.text.createAnnotatedString
import com.example.financeapp.ui.theme.LocalDimensions


@Composable
fun FaTermsAndCondition(
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    onTermsAndConditionClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChanged,
        )

        Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions8))

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
            modifier = Modifier.clickable(onClick = onTermsAndConditionClicked)
        )
    }
}

@FaPreview
@Composable
private fun FaTermsAndConditionPreview() {
    PreviewSurface {
        FaTermsAndCondition(
            isChecked = true,
            onCheckedChanged = {  },
            onTermsAndConditionClicked = {  }
        )

        FaTermsAndCondition(
            isChecked = false,
            onCheckedChanged = {  },
            onTermsAndConditionClicked = {  }
        )
    }
}