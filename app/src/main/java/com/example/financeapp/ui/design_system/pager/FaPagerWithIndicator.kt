package com.example.financeapp.ui.design_system.pager

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.theme.LocalDimensions
import com.example.financeapp.ui.theme.LocalShapes


@Composable
fun FaPagerWithIndicator(
    pageCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    content: @Composable (pageIndex: Int) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState
        ) { page ->
            content(page)
        }

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        PagerIndicator(
            currentPage = pagerState.currentPage,
            pageCount = pageCount
        )
    }
}


@Composable
private fun PagerIndicator(
    currentPage: Int,
    pageCount: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            val shape = LocalShapes.current.dots

            val width = animateDpAsState(
                targetValue =
                    LocalDimensions.current.dimensions8,
                label = "widthAnimation"
            )
            val color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onPrimary
            }

            Box(
                modifier = Modifier
                    .width(width.value)
                    .size(LocalDimensions.current.dimensions8)
                    .clip(shape)
                    .background(color)
            )
        }
    }
}


@Composable
@FaPreview
fun PagerIndicatorWithPreview() {
    val pagerState = rememberPagerState(
        pageCount = { 5 }
    )
    PreviewSurface {
        FaPagerWithIndicator(
            pageCount = 5,
            pagerState = pagerState,
        ) {

        }
    }
}
