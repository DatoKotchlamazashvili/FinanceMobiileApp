package com.example.financeapp.ui.design_system.switchs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.TextType

@Composable
fun FaSwitch(
    title: String,
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FaText(text = title, textType = TextType.LABEL_LARGE)

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChanged
            )
        }

        HorizontalDivider()
    }

}

@FaPreview
@Composable
private fun FaSwitchPreview() {
    PreviewSurface {
        FaSwitch(
            title = "sakdl",
            checked = false,
            onCheckedChanged = { }
        )

        FaSwitch(
            title = "sakdl",
            checked = true,
            onCheckedChanged = { }
        )
    }
}