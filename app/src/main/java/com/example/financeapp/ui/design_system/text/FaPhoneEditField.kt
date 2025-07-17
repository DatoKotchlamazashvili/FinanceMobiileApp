package com.example.financeapp.ui.design_system.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.FlagCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.LocalDimensions
import com.example.financeapp.ui.theme.LocalShapes


data class Country(val code: String, val flagRes: ImageVector)

@Composable
fun PhoneInput(
    countries: List<Country>,
    selected: Country,
    onSelectCountry: (Country) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(LocalDimensions.current.dimensions56)
            .border(BorderStroke(1.dp, Color.LightGray), LocalShapes.current.button)
            .padding(horizontal = LocalDimensions.current.dimensions8)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = { menuExpanded = true })
                .padding(end = LocalDimensions.current.dimensions12)
        ) {
            Icon(
                imageVector = selected.flagRes,
                contentDescription = "${selected.code} flag",
                modifier = Modifier.size(LocalDimensions.current.dimensions24)
            )
            Spacer(Modifier.width(LocalDimensions.current.dimensions4))
            FaText(text = selected.code, TextType.LABEL_LARGE)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.size(LocalDimensions.current.dimensions16)
            )

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(country.code) },
                        leadingIcon = {
                            Icon(
                                imageVector = country.flagRes,
                                contentDescription = country.code,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        onClick = {
                            onSelectCountry(country)
                            menuExpanded = false
                        }
                    )
                }
            }
        }

        Box(
            Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color.LightGray)
        )

        Spacer(Modifier.width(LocalDimensions.current.dimensions12))

        TextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            placeholder = { FaText("85788773880", textType = TextType.LABEL_LARGE_VARIANT) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PhoneInputPreview() {
    val countryList = listOf<Country>(
        Country(
            code = "Geo",
            flagRes = Icons.Default.Flag,
        ),
        Country(
            code = "Por",
            flagRes = Icons.Default.FlagCircle,
        ),
    )
    var selected by remember { mutableStateOf(countryList[0]) }
    var phone by remember { mutableStateOf("") }

    PhoneInput(
        countries = countryList,
        selected = selected,
        onSelectCountry = { selected = it },
        phoneNumber = phone,
        onPhoneNumberChange = { phone = it },
        modifier = Modifier.padding(16.dp)
    )
}
