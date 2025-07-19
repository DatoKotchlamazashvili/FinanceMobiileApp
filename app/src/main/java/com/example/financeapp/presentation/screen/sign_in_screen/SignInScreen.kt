package com.example.financeapp.presentation.screen.sign_in_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaEditField
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.SpanType
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.design_system.text.createAnnotatedString
import com.example.financeapp.ui.theme.LocalDimensions

data class SignInUiState(val email: String, val password: String)

sealed interface SignInEvents {
    data class OnEmailChanged(val email: String) : SignInEvents
    data class OnPasswordChanged(val password: String) : SignInEvents

    data object OnSignInClicked : SignInEvents

    data object OnSignUpClicked : SignInEvents
}

@Composable
fun SignInScreenContent(
    uiState: SignInUiState,
    onEvent: (SignInEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.30f)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = LocalDimensions.current.dimensions24)
            ) {
                FaText(
                    text = "SHIELDPAY",
                    textType = TextType.HEADLINE_SMALL,
                    textColor = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = LocalDimensions.current.dimensions24)
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions2))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_shieldpay_left),
                        contentDescription = null
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_shieldpay_right),
                        contentDescription = null
                    )

                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(LocalDimensions.current.dimensions24),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions32))

            FaText(
                text = "Welcome Back",
                textType = TextType.HEADLINE_SMALL,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions32))

            FaEditField(
                onValueChange = { onEvent(SignInEvents.OnEmailChanged(it)) },
                hint = "JohnDoe@gmail.com",
                title = "Email Address",
                value = uiState.email
            )
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions24))

            FaEditField(
                onValueChange = { onEvent(SignInEvents.OnPasswordChanged(it)) },
                hint = "",
                title = "Password",
                value = uiState.password,
                isSecret = true
            )

            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

            FaText(
                text = "Forgot Password?",
                textColor = MaterialTheme.colorScheme.secondary,
                textType = TextType.LABEL_LARGE,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions32))

            FaButton(
                text = "Sign In",
                onClick = { onEvent(SignInEvents.OnSignInClicked) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions32))

            FaText(
                text = createAnnotatedString(
                    fullText = "Don’t have an account? Sign Up",
                    spans = listOf(
                        SpanType.HeadingSpan(
                            text = "Sign Up",
                            styles = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    )
                ),
                textType = TextType.LABEL_LARGE,
                modifier = Modifier
                    .clickable(onClick = { onEvent(SignInEvents.OnSignUpClicked) })
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                textColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@FaPreview
@Composable
private fun SignInScreenContentPreview() {
    PreviewSurfaceStatic {
        SignInScreenContent(
            uiState = SignInUiState(email = "", password = ""),
            onEvent = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
