package com.example.financeapp.ui.design_system.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.LocalDimensions

@Composable
fun PreviewSurface(content: @Composable () -> Unit) {
    FinanceAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(LocalDimensions.current.dimensions16)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))
                content()
            }
        }
    }
}



@Composable
fun PreviewSurfaceStatic(content: @Composable () -> Unit) {
    FinanceAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content()
        }
    }
}
