package com.example.financeapp.presentation.screen.change_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.button.ButtonType
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.FaUnderlineTextField
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.theme.LocalDimensions

data class ChangePasswordUiState(val oldPassword: String, val newPassword: String)

sealed interface ChangePasswordEvents {
    data class OnOldPasswordChanged(val oldPassword: String) : ChangePasswordEvents

    data class OnNewPasswordChanged(val newPassword: String) : ChangePasswordEvents

    data object OnSaveClicked : ChangePasswordEvents
    data object OnCancelClicked : ChangePasswordEvents
}


@Composable
fun ChangePasswordScreenContent(
    uiState: ChangePasswordUiState,
    onEvent: (ChangePasswordEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        FaText(
            text = "Change Password",
            textType = TextType.HEADLINE_SMALL,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        FaUnderlineTextField(
            value = uiState.oldPassword,
            onValueChange = { onEvent(ChangePasswordEvents.OnOldPasswordChanged(it)) },
            isSecret = true
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaUnderlineTextField(
            value = uiState.newPassword,
            onValueChange = { onEvent(ChangePasswordEvents.OnNewPasswordChanged(it)) },
            isSecret = true,
            placeholder = "Re-password new password"
        )

        Spacer(modifier = Modifier.weight(1f))

        FaButton(
            text = "Save",
            onClick = { onEvent(ChangePasswordEvents.OnSaveClicked) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FaButton(
            text = "Cancel",
            onClick = { onEvent(ChangePasswordEvents.OnCancelClicked) },
            modifier = Modifier.fillMaxWidth(),
            buttonType = ButtonType.NOT_FILLED_STROKE
        )

    }
}

@Preview
@Composable
private fun ChangePasswordScreenContentPreview() {
    PreviewSurfaceStatic {
        ChangePasswordScreenContent(
            uiState = ChangePasswordUiState(
                oldPassword = "dfgfdg",
                newPassword = ""
            ),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}