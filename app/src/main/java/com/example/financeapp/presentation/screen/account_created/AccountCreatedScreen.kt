package com.example.financeapp.presentation.screen.account_created

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.SpanType
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.design_system.text.createAnnotatedString
import com.example.financeapp.ui.theme.LocalDimensions

sealed interface AccountCreatedEvents {
    data object OnContinueClicked : AccountCreatedEvents
}

@Composable
fun AccountCreatedScreen(
    onEvent: (AccountCreatedEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FaText(
                text = "Account Created",
                textType = TextType.HEADLINE_SMALL,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

            FaText(
                text = "Your account has been created successfully.\nPress continue to continue using the app",
                textType = TextType.LABEL_SMALL,
                textColor = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.2f))

            Image(
                painter = painterResource(R.drawable.ic_account_created),
                contentDescription = null,
                modifier = Modifier.heightIn(max = 220.dp, min = 120.dp)
            )
        }

        FaButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.dimensions16),
            text = "Continue",
            onClick = { onEvent(AccountCreatedEvents.OnContinueClicked) }
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FaText(
            text = createAnnotatedString(
                fullText = "By clicking continue, you agree to our Terms and Conditions",
                spans = listOf(
                    SpanType.UnderLineSpan(
                        text = "Terms and Conditions",
                        styles = SpanStyle(color = MaterialTheme.colorScheme.primary)
                    )
                )
            ),
            textType = TextType.LABEL_LARGE,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))
    }
}

@FaPreview
@Composable
fun AccountCreatedScreenPreview() {
    PreviewSurfaceStatic {
        AccountCreatedScreen(
            onEvent = { }, modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}