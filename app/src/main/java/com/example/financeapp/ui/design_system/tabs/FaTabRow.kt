package com.example.financeapp.ui.design_system.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface

enum class TabType {
    PROGRESS, DONE
}

val tabItems = listOf(
    TabItem(
        title = "Progress",
        tabType = TabType.PROGRESS
    ),
    TabItem(
        title = "Done",
        tabType = TabType.DONE
    ),
)

@Composable
fun FaTabRow(
    selectedTab: TabType,
    onTabSelected: (TabType) -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {

    val selectedIndex = tabItems.indexOfFirst { it.tabType == selectedTab }
    val pagerState = rememberPagerState { tabItems.size }

    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(selectedIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            onTabSelected(tabItems[pagerState.currentPage].tabType)
        }
    }
    Column(
        modifier = modifier
    ) {
        TabRow(selectedTabIndex = selectedIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = { onTabSelected(item.tabType) },
                    text = { Text(item.title) },
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }


        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}

@FaPreview
@Composable
private fun FaTabRowPreview() {
    PreviewSurface {
        var current by remember { mutableStateOf(TabType.PROGRESS) }
        FaTabRow(
            selectedTab = current,
            onTabSelected = { current = it },
            content = {}
        )
    }
}