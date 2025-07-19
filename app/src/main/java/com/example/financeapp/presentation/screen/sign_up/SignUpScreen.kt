package com.example.financeapp.presentation.screen.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.design_system.button.ButtonType
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.button.FaTermsAndCondition
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaEditField
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.SpanType
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.design_system.text.createAnnotatedString
import com.example.financeapp.ui.theme.LocalDimensions


data class SignUpScreenUiState(
    val email: String = "",
    val fullName: String = "",
    val password: String = "",
    val termsChecked: Boolean = false,
)

sealed interface SignUpScreenEvents {
    data class OnFullNameChanged(val fullName: String) : SignUpScreenEvents
    data class OnEmailChanged(val email: String) : SignUpScreenEvents
    data class OnPasswordChanged(val password: String) : SignUpScreenEvents

    data object OnTermsChanged : SignUpScreenEvents

    data object OnSignUpClicked : SignUpScreenEvents
    data object WithPhoneNumberClicked : SignUpScreenEvents

    data object OnSignInClicked : SignUpScreenEvents
    data object OnTermsAndConditionClick : SignUpScreenEvents
}

@Composable
fun SignUpScreenContent(
    uiState: SignUpScreenUiState,
    onEvent: (SignUpScreenEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = LocalDimensions.current.dimensions16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaText(
            text = "Welcome!",
            textType = TextType.HEADLINE_SMALL,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaEditField(
            onValueChange = { onEvent(SignUpScreenEvents.OnFullNameChanged(it)) },
            hint = "John Doe",
            title = "Full Name"
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions24))

        FaEditField(
            onValueChange = { onEvent(SignUpScreenEvents.OnEmailChanged(it)) },
            hint = "JohnDoe@Gmail.com",
            title = "Email"
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions24))

        FaEditField(
            onValueChange = { onEvent(SignUpScreenEvents.OnPasswordChanged(it)) },
            hint = "",
            title = "Password",
            isSecret = true
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions24))

        FaTermsAndCondition(
            isChecked = uiState.termsChecked,
            onCheckedChanged = { onEvent(SignUpScreenEvents.OnTermsChanged) },
            onTermsAndConditionClicked = { onEvent(SignUpScreenEvents.OnTermsAndConditionClick) },
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FaButton(
                text = "Sign Up",
                onClick = { onEvent(SignUpScreenEvents.OnSignUpClicked) },
                buttonType = ButtonType.FILLED_TONAL,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))
            FaButton(
                text = "With Phone Number",
                onClick = { onEvent(SignUpScreenEvents.WithPhoneNumberClicked) },
                buttonType = ButtonType.NOT_FILLED_STROKE,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))
            FaText(
                text = createAnnotatedString(
                    fullText = "Already have an account? Sign In",
                    spans = listOf(
                        SpanType.HeadingSpan(
                            text = "Sign In",
                            styles = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    )
                ),
                textType = TextType.LABEL_SMALL,
                modifier = Modifier
                    .clickable { onEvent(SignUpScreenEvents.OnSignInClicked) }
                    .padding(vertical = LocalDimensions.current.dimensions8)
            )
        }

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))
    }
}

@FaPreview
@Composable
fun SignUpScreenContentPreview() {
    PreviewSurfaceStatic {
        SignUpScreenContent(
            uiState = SignUpScreenUiState(),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}