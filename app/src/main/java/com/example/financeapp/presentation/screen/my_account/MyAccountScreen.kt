package com.example.financeapp.presentation.screen.my_account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.button.ButtonType
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.switchs.FaSwitch
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.SpanType
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.design_system.text.createAnnotatedString
import com.example.financeapp.ui.theme.LocalDimensions

data class MyAccountUiState(val email: String = "", val hideAccount: Boolean = false)

sealed interface MyAccountEvents {
    data object OnChangePasswordClicked : MyAccountEvents
    data object OnLogoutClicked : MyAccountEvents
    data object OnDeleteAccountClicked : MyAccountEvents

    data object OnHideAccountChanged : MyAccountEvents
}

@Composable
fun MyAccountScreenContent(
    uiState: MyAccountUiState,
    onEvent: (MyAccountEvents) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FaText(text = "Account", textType = TextType.HEADLINE_SMALL, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions64))
        FaSwitch(
            title = "Hide Account",
            checked = uiState.hideAccount,
            onCheckedChanged = { onEvent(MyAccountEvents.OnHideAccountChanged) }
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.35f))

        FaText(
            text = "Make sure you can remember you password, as you’ll need it to sign back in",
            textType = TextType.LABEL_SMALL,
            textColor = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaText(
            text = uiState.email,
            textType = TextType.LABEL_SMALL,
            textColor = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.weight(1f))

        FaButton(
            text = "Change Password",
            onClick = { onEvent(MyAccountEvents.OnChangePasswordClicked) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))


        FaButton(
            text = "Log Out",
            onClick = { onEvent(MyAccountEvents.OnLogoutClicked) },
            modifier = Modifier.fillMaxWidth(), buttonType = ButtonType.NOT_FILLED_STROKE
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))


        FaText(
            text = createAnnotatedString(
                fullText = "Delete account",
                spans = listOf(
                    SpanType.UnderLineSpan(
                        text = "Delete account",
                        styles = SpanStyle(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                )
            ),
            textType = TextType.LABEL_SMALL,
            textColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable(onClick = { onEvent(MyAccountEvents.OnDeleteAccountClicked) })
        )
    }
}

@FaPreview
@Composable
private fun MyAccountScreenPreview() {
    PreviewSurfaceStatic {
        MyAccountScreenContent(
            uiState = MyAccountUiState(email = "ed******@gmail.com", true),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        )
    }
}