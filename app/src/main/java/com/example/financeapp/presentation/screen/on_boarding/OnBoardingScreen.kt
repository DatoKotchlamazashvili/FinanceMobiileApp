package com.example.financeapp.presentation.screen.on_boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.button.ButtonType
import com.example.financeapp.ui.design_system.button.FaButton
import com.example.financeapp.ui.design_system.pager.FaPagerWithIndicator
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.SpanType
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.design_system.text.createAnnotatedString
import com.example.financeapp.ui.theme.LocalDimensions
import kotlinx.coroutines.launch


@Composable
fun OnBoardingScreen(
    onLoginNowClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val onBoardings = remember { getOnBoardingData() }
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { onBoardings.size }
        )
        val coroutineScope = rememberCoroutineScope()

        FaPagerWithIndicator(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = LocalDimensions.current.dimensions24),
            pageCount = onBoardings.size,
            pagerState = pagerState
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = LocalDimensions.current.dimensions16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight(0.55f), contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(onBoardings[page].imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(LocalDimensions.current.dimensions180 + 40.dp)
                    )
                }
                FaText(
                    text = onBoardings[page].mainText,
                    textType = TextType.HEADLINE_SMALL,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))

                FaText(
                    text = onBoardings[page].subtext,
                    textType = TextType.LABEL_LARGE
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
            }
        }

        FaButton(
            text = onBoardings[pagerState.currentPage].mainButtonText,
            buttonType = ButtonType.FILLED_TONAL,
            onClick = {
                if (pagerState.currentPage < onBoardings.lastIndex) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onCreateAccountClicked()
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        when (onBoardings[pagerState.currentPage].subButtonType) {
            OnBoardingSubButtonType.UNDERLINE_TEXT -> FaText(
                text = createAnnotatedString(
                    fullText = onBoardings[pagerState.currentPage].subButtonText,
                    spans = listOf(
                        SpanType.UnderLineSpan(
                            text = onBoardings[pagerState.currentPage].subButtonText,
                            styles = SpanStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                ),
                textType = TextType.LABEL_LARGE,
                modifier = Modifier.clickable(onClick = {
                    if (pagerState.currentPage < onBoardings.lastIndex) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(onBoardings.lastIndex)
                        }
                    } else {
                        onLoginNowClicked()
                    }
                })
            )

            OnBoardingSubButtonType.SUB_BUTTON -> FaButton(
                text = onBoardings[pagerState.currentPage].subButtonText,
                buttonType = ButtonType.NOT_FILLED_STROKE,
                onClick = {
                    if (pagerState.currentPage < onBoardings.lastIndex) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(onBoardings.lastIndex)
                        }
                    } else {
                        onLoginNowClicked()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@FaPreview
@Composable
private fun OnBoardingScreenPreview() {
    PreviewSurface {
        OnBoardingScreen(
            onLoginNowClicked = { },
            onCreateAccountClicked = { },
            modifier = Modifier
                .fillMaxSize()

        )
    }
}