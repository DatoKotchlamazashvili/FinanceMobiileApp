package com.example.financeapp.presentation.screen.phone_number_auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.FlagCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.button.ButtonType
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.Country
import com.example.financeapp.ui.design_system.text.FaPhoneEditField
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.theme.LocalDimensions


fun getCountries(): List<Country> = listOf<Country>(
    Country(
        code = "Geo",
        flagRes = Icons.Default.Flag,
    ),
    Country(
        code = "Por",
        flagRes = Icons.Default.FlagCircle,
    ),
)

data class PhoneNumberAuthScreenUiState(
    val phoneNumber: String = "",
    val counties: List<Country> = getCountries(),
    val selectedCountry: Country = getCountries()[0],
)

sealed interface PhoneNumberAuthScreenEvents {
    data class OnPhoneNumberChanged(val phoneNumber: String) : PhoneNumberAuthScreenEvents

    data class OnCountryChanged(val country: Country) : PhoneNumberAuthScreenEvents


    data object OnSendCodeClicked : PhoneNumberAuthScreenEvents
    data object SignUpWithEmailClicked : PhoneNumberAuthScreenEvents
}


@Composable
fun PhoneNumberAuthScreenContent(
    uiState: PhoneNumberAuthScreenUiState,
    onEvent: (PhoneNumberAuthScreenEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        FaText(
            text = "Your Phone Number",
            textType = TextType.HEADLINE_SMALL,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FaText(
            text = "Enter your mobile number to register an account",
            textType = TextType.LABEL_SMALL
        )


        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions32))


        FaPhoneEditField(
            countries = uiState.counties,
            selected = uiState.selectedCountry,
            onSelectCountry = { onEvent(PhoneNumberAuthScreenEvents.OnCountryChanged(it)) },
            phoneNumber = uiState.phoneNumber,
            onPhoneNumberChange = { onEvent(PhoneNumberAuthScreenEvents.OnPhoneNumberChanged(it)) }
        )

        Spacer(modifier = Modifier.weight(1f))

        FaButton(
            text = "Send Code",
            onClick = { onEvent(PhoneNumberAuthScreenEvents.OnSendCodeClicked) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FaButton(
            text = "Sign Up With Email",
            onClick = { onEvent(PhoneNumberAuthScreenEvents.SignUpWithEmailClicked) },
            modifier = Modifier.fillMaxWidth(),
            buttonType = ButtonType.NOT_FILLED_STROKE
        )
    }
}

@Preview
@Composable
private fun PhoneNumberAuthScreenPreview() {
    PreviewSurfaceStatic {
        PhoneNumberAuthScreenContent(
            uiState = PhoneNumberAuthScreenUiState(),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}