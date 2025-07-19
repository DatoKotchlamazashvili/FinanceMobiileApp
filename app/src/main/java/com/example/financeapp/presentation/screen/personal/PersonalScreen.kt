package com.example.financeapp.presentation.screen.personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.button.ButtonType
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.text.FaCalendarTextField
import com.example.financeapp.ui.design_system.text.FaEditField
import com.example.financeapp.ui.design_system.text.FaUnderlineTextField
import com.example.financeapp.ui.theme.LocalDimensions

data class PersonalUiState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val birthDay: Long = 0L,
    val location: String = "",
    val description: String = "",
)

sealed interface PersonalEvents {
    data class OnFirstNameChanged(val firstName: String) : PersonalEvents
    data class OnLastNameChanged(val lastName: String) : PersonalEvents
    data class OnPhoneNumberChanged(val phoneNumber: String) : PersonalEvents
    data class OnBirthdayChanged(val birthDay: Long) : PersonalEvents
    data class OnLocationChanged(val location: String) : PersonalEvents
    data class OnDescriptionChanged(val description: String) : PersonalEvents

    data object OnSaveClick : PersonalEvents
    data object OnCancelClick : PersonalEvents
}

@Composable
fun PersonalScreenContent(
    uiState: PersonalUiState,
    onEvent: (PersonalEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        FaUnderlineTextField(
            value = uiState.firstName,
            onValueChange = { onEvent(PersonalEvents.OnFirstNameChanged(it)) },
            placeholder = "First Name"
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaUnderlineTextField(
            value = uiState.lastName,
            onValueChange = { onEvent(PersonalEvents.OnLastNameChanged(it)) },
            placeholder = "Last Name"
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaUnderlineTextField(
            value = uiState.phoneNumber,
            onValueChange = { onEvent(PersonalEvents.OnPhoneNumberChanged(it)) },
            placeholder = "Phone Number",
            leadingIcon = Icons.Default.Phone
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaCalendarTextField(
            onDateSelected = { onEvent(PersonalEvents.OnBirthdayChanged(it)) },
            selectedDateMillis = uiState.birthDay
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))
        FaUnderlineTextField(
            value = uiState.location,
            onValueChange = { onEvent(PersonalEvents.OnLocationChanged(it)) },
            placeholder = "Location",
            leadingIcon = Icons.Default.LocationOn
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        FaEditField(
            onValueChange = { onEvent(PersonalEvents.OnDescriptionChanged(it)) },
            hint = "",
            title = "Description"
        )

        Spacer(modifier = Modifier.weight(1f))

        FaButton(
            text = "Save",
            onClick = { onEvent(PersonalEvents.OnSaveClick) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FaButton(
            text = "Cancel",
            onClick = { onEvent(PersonalEvents.OnCancelClick) },
            modifier = Modifier.fillMaxWidth(),
            buttonType = ButtonType.NOT_FILLED_STROKE
        )


    }
}

@Preview
@Composable
private fun PersonalScreenContentPreview() {
    PreviewSurfaceStatic {
        PersonalScreenContent(
            uiState = PersonalUiState(location = "sdfkjdsf"),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}