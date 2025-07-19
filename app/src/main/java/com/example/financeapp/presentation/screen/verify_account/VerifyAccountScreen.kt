package com.example.financeapp.presentation.screen.verify_account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.FaUnderLinePin
import com.example.financeapp.ui.design_system.text.SpanType
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.design_system.text.createAnnotatedString
import com.example.financeapp.ui.theme.LocalDimensions

data class VerifyAccountUiState(
    val phoneNumber: String = "995591167223",
    val code: String = "",
)

sealed interface VerifyAccountScreenEvents {
    data class OnCodeChanged(val code: String) : VerifyAccountScreenEvents

    data object OnResendCodeClicked : VerifyAccountScreenEvents
    data object OnVerifyClicked : VerifyAccountScreenEvents
}


@Composable
fun VerifyAccountScreenContent(
    uiState: VerifyAccountUiState,
    onEvent: (VerifyAccountScreenEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        FaText(
            text = "Verify Account",
            textType = TextType.HEADLINE_SMALL,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FaText(
            text = createAnnotatedString(
                fullText = "Enter 4 digit code we have sent to +${uiState.phoneNumber}",
                spans = listOf(
                    SpanType.HeadingSpan(
                        text = "+${uiState.phoneNumber}", styles = SpanStyle(
                            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                        )
                    )
                )

            ), textType = TextType.LABEL_SMALL, textColor = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaUnderLinePin(
            pin = uiState.code,
            onPinChange = { onEvent(VerifyAccountScreenEvents.OnCodeChanged(code = it)) },
            length = 4,
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaText(text = "Haven’t received verification code?", textType = TextType.LABEL_SMALL)
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))

        FaText(
            text = createAnnotatedString(
                fullText = "Resend Code", spans = listOf(
                    SpanType.UnderLineSpan(
                        text = "Resend Code", styles = SpanStyle(
                            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                        )
                    )
                )
            ), textType = TextType.LABEL_LARGE, modifier = Modifier.clickable(onClick = {
                    onEvent(
                        VerifyAccountScreenEvents.OnResendCodeClicked
                    )
                }), textColor = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.weight(1f))

        FaButton(
            text = "Verify",
            onClick = { onEvent(VerifyAccountScreenEvents.OnVerifyClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@FaPreview
@Composable
fun VerifyAccountScreenPreview() {
    PreviewSurfaceStatic {
        VerifyAccountScreenContent(
            uiState = VerifyAccountUiState(),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}