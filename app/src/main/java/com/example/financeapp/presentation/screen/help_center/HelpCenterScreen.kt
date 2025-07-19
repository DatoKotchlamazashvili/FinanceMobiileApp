package com.example.financeapp.presentation.screen.help_center

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.design_system.preview.PreviewSurfaceStatic
import com.example.financeapp.ui.design_system.tabs.FaMenuTab
import com.example.financeapp.ui.design_system.text.FaText
import com.example.financeapp.ui.design_system.text.SearchBar
import com.example.financeapp.ui.design_system.text.TextType
import com.example.financeapp.ui.theme.LocalDimensions

data class Question(val mainText: String, val subText: String)

data class HelpCenterUiState(
    val search: String,
    val questions: List<Question> = emptyList<Question>(),
)


sealed interface HelpCenterEvents {
    data class OnSearchChanged(val text: String) : HelpCenterEvents


}

@Composable
fun HelpCenterScreenContent(
    uiState: HelpCenterUiState,
    onEvent: (HelpCenterEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FaText(
            text = "Help Center",
            textType = TextType.HEADLINE_SMALL,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions48))

        SearchBar(
            query = uiState.search,
            onQueryChange = { onEvent(HelpCenterEvents.OnSearchChanged(it)) }
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions40))

        LazyColumn {
            items(uiState.questions) {
                FaMenuTab(
                    mainText = it.mainText,
                    subText = it.subText,
                )
            }
        }
    }
}

@Preview
@Composable
private fun HelpCenterScreenPreview() {
    PreviewSurfaceStatic {
        HelpCenterScreenContent(
            uiState = HelpCenterUiState(
                search = "",
                questions = listOf(
                    Question("sdfkjds", "sdkfjdsfdsjfkldsjfkld"),
                    Question("sdfghfghfkjds", "sdkfjdsfdsjfkldsjdfgdfgfdgfkld"),
                    Question("sdffghgfhkjds", "hgfh"),
                    Question("sdfghgkjds", "sdkfjdsfdsjffghfzhgfkldsjfkld"),
                    Question("sfghdfkjds", "sdkfjdsfdsjfghgfhgfhfkldsjfkld")

                )
            ),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}