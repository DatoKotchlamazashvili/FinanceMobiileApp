package com.example.financeapp.presentation.screen.confirm_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.call_dial.CallIconType
import com.example.financeapp.ui.design_system.call_dial.FaCallDial
import com.example.financeapp.ui.design_system.dots.FaPasswordDots
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.TextType

data class ConfirmPasswordUiState(val password: String = "1276")

sealed interface ConfirmPasswordEvents {
    data class OnDialNumberClick(val number: Int) : ConfirmPasswordEvents
    data class OnDialIconClick(val callIconType: CallIconType) : ConfirmPasswordEvents

}

@Composable
fun CreatePasswordScreenContent(
    uiState: ConfirmPasswordUiState,
    onEvent: (ConfirmPasswordEvents) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        FaText(
            text = "Confirm Password",
            textType = TextType.HEADLINE_SMALL,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.3f))

        FaPasswordDots(
            totalNumberOfDots = 4,
            selectedDotsNumbers = uiState.password.length
        )
        Spacer(modifier = Modifier.weight(1f))
        FaCallDial(
            onNumberClicked = { onEvent(ConfirmPasswordEvents.OnDialNumberClick(it)) },
            onIconClicked = { onEvent(ConfirmPasswordEvents.OnDialIconClick(it)) }
        )
    }

}

@FaPreview
@Composable
fun CreatePasswordScreenContentPreview() {
    PreviewSurfaceStatic {
        CreatePasswordScreenContent(
            uiState = ConfirmPasswordUiState(),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}