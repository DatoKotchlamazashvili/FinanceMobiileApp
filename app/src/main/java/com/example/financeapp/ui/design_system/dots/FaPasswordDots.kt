package com.example.financeapp.ui.design_system.dots

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.theme.LocalDimensions
import com.example.financeapp.ui.theme.LocalShapes


@Composable
fun FaPasswordDots(
    totalNumberOfDots: Int,
    selectedDotsNumbers: Int,
    modifier: Modifier = Modifier,
) {

    Row(modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions32),
        verticalAlignment = Alignment.CenterVertically

    ) {
        (0..totalNumberOfDots-1).map {
            val isSelected = it < selectedDotsNumbers
            val color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }

            Box(
                modifier = Modifier
                    .size(LocalDimensions.current.dimensions16)
                    .clip(LocalShapes.current.dots)
                    .background(color)
            )
        }
    }
}


@FaPreview
@Composable
private fun FaPasswordDotsPreview() {
    PreviewSurface {
        FaPasswordDots(
            totalNumberOfDots = 4,
            selectedDotsNumbers =2
        )
    }
}