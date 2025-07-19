package com.example.financeapp.presentation.screen.forgot_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaEditField
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.theme.LocalDimensions

data class ForgotPasswordUiState(
    val email: String,
)

sealed interface ForgotPasswordEvent {
    data class OnEmailChanged(val email: String) : ForgotPasswordEvent

    data object OnSendCodeClicked : ForgotPasswordEvent
}

@Composable
fun ForgotPasswordScreenContent(
    uiState: ForgotPasswordUiState,
    onEvent: (ForgotPasswordEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        FaText(
            text = "Forgot Password",
            textType = TextType.HEADLINE_SMALL,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_forget_password),
                contentDescription = null,
                modifier = Modifier.heightIn(min = 100.dp, max = 180.dp)
            )

        }

        FaText(
            text = "Enter your registered email below to receive password reset instruction",
            textType = TextType.LABEL_SMALL,
            textColor = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaEditField(
            onValueChange = { onEvent(ForgotPasswordEvent.OnEmailChanged(it)) },
            value = uiState.email,
            hint = "",
            title = "Forgot Password",
            isSecret = true
        )

        Spacer(modifier = Modifier.weight(1f))

        FaButton(
            text = "Send Verification Code",
            onClick = { onEvent(ForgotPasswordEvent.OnSendCodeClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@FaPreview
@Composable
private fun ForgotPasswordScreenPreview() {
    PreviewSurfaceStatic {
        ForgotPasswordScreenContent(
            uiState = ForgotPasswordUiState(
                email = ""
            ),
            onEvent = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}