package com.example.financeapp.ui.design_system.call_dial

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.theme.LocalDimensions


fun getCallDialData(): List<CallDialType> =
    (1..9).map { CallDialType.Number(it) } + CallDialType.Empty + CallDialType.Number(0) + CallDialType.Icon(
        iconRes = Icons.AutoMirrored.Filled.Backspace,
        callIconType = CallIconType.DELETE
    )

@Composable
fun FaCallDial(
    data: List<CallDialType> = getCallDialData(),
    onNumberClicked: (Int) -> Unit,
    onIconClicked: (CallIconType) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxWidth().height(300.dp)
    ) {
        items(data) { type ->
            when (type) {
                CallDialType.Empty -> FaCallDialBoxEmpty()
                is CallDialType.Icon -> FaCallDialBoxIcon(
                    icon = type.iconRes,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalDimensions.current.dimensions56)
                        .clickable { onIconClicked(type.callIconType) }
                )

                is CallDialType.Number -> FaCallDialBoxNumber(
                    number = type.number,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalDimensions.current.dimensions56)
                        .clickable { onNumberClicked(type.number) }
                )
            }
        }
    }
}

@Composable
fun FaCallDialBoxNumber(
    modifier: Modifier = Modifier,
    number: Int,
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ) {
        FaText(
            text = number.toString(),
            textType = TextType.HEADLINE_SMALL,
        )
    }
}

@Composable
fun FaCallDialBoxIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun FaCallDialBoxEmpty(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(LocalDimensions.current.dimensions56)
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {
    }
}

@FaPreview
@Composable
private fun FaCallDialPreview() {
    PreviewSurface {
        FaCallDial(
            data = getCallDialData(),
            onNumberClicked = {},
            onIconClicked = { }
        )
    }
}