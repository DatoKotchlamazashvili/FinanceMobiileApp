package com.example.financeapp.ui.design_system.tabs

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.theme.LocalDimensions

@Composable
fun FaMenuTab(
    mainText: String,
    subText: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FaText(
                text = mainText,
                fontWeight = if (expanded) FontWeight.Bold else FontWeight.Normal,
                textType = TextType.HEADLINE_SMALL
            )

            val rotationDegree by animateFloatAsState(
                targetValue = if (expanded) 0f else -90f,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(LocalDimensions.current.dimensions24)
                    .rotate(rotationDegree),
                tint = if (expanded) Color.Black else Color.Gray
            )
        }

        if (expanded) {
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
            FaText(
                text = subText,
                textType = TextType.LABEL_SMALL
            )
        }
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))
        HorizontalDivider()
    }
}


@FaPreview
@Composable
private fun FaMenuTabPreview() {
    PreviewSurface {
        FaMenuTab(
            mainText = "Dato",
            subText = "skfdjgdfjgfdgjkfgjfklgjflgjfdgklfglkfgd"
        )
    }
}