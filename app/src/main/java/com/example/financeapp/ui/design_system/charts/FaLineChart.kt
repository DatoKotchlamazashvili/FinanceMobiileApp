package com.example.financeapp.ui.design_system.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.design_system.preview.FaPreview
import com.example.financeapp.ui.design_system.preview.PreviewSurface

@Composable
fun LineChartCard(
    modifier: Modifier = Modifier,
    title: String = "Total Balance",
    amount: String = "\$2,885.00",
    labels: List<String> = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
    series1: List<Float> = listOf(20f, 22f, 27f, 18f, 30f, 24f, 20f),
    series2: List<Float> = listOf(15f, 14f, 25f, 5f, 26f, 16f, 14f),
    maxY: Float = 40f,
    gridLines: Int = 4,
    chartHeight: Dp = 180.dp,
    chartPadding: Dp = 16.dp,
) {
    var dropdownOpen by remember { mutableStateOf(false) }
    var selectedRange by remember { mutableStateOf("Week") }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            LineChartHeader(
                title = title,
                amount = amount,
                selectedRange = selectedRange,
                onRangeClick = { dropdownOpen = true },
                dropdownOpen = dropdownOpen,
                onRangeSelected = {
                    selectedRange = it
                    dropdownOpen = false
                })

            Spacer(Modifier.height(12.dp))

            LineChartBody(
                series1 = series1,
                series2 = series2,
                maxY = maxY,
                gridLines = gridLines,
                chartHeight = chartHeight,
                chartPadding = chartPadding
            )

            Spacer(Modifier.height(8.dp))

            XAxisLabels(labels = labels, chartPadding = chartPadding)
        }
    }
}

@Composable
private fun LineChartHeader(
    title: String,
    amount: String,
    selectedRange: String,
    onRangeClick: () -> Unit,
    dropdownOpen: Boolean,
    onRangeSelected: (String) -> Unit,
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Text(
                amount,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        Box {
            Text(
                selectedRange,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surface, RoundedCornerShape(6.dp)
                    )
                    .clickable(onClick = onRangeClick)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.onSurface,
            )
            DropdownMenu(
                expanded = dropdownOpen, onDismissRequest = { onRangeSelected(selectedRange) }) {
                listOf("Week", "Month", "Year").forEach { range ->
                    DropdownMenuItem(text = { Text(range) }, onClick = { onRangeSelected(range) })
                }
            }
        }
    }
}

@Composable
private fun LineChartBody(
    series1: List<Float>,
    series2: List<Float>,
    maxY: Float,
    gridLines: Int,
    chartHeight: Dp,
    chartPadding: Dp,
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(chartHeight)
    ) {
        val w = size.width
        val h = size.height
        val pad = chartPadding.toPx()
        val usableW = w - pad * 2
        val usableH = h - pad * 2

        val stepY = usableH / gridLines
        repeat(gridLines + 1) { i ->
            val y = pad + i * stepY
            drawLine(
                color = Color.Gray,
                start = Offset(pad, y),
                end = Offset(w - pad, y),
                strokeWidth = 1f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }

        fun pointFor(series: List<Float>, idx: Int): Offset {
            val x = pad + idx * (usableW / (series.size - 1))
            val y = pad + usableH * (1f - (series[idx] / maxY).coerceIn(0f, 1f))
            return Offset(x, y)
        }

        fun drawSmoothed(series: List<Float>, color: Color) {
            if (series.size < 2) return
            val path = Path().apply {
                moveTo(pointFor(series, 0).x, pointFor(series, 0).y)
                for (i in 1 until series.size) {
                    val prev = pointFor(series, i - 1)
                    val curr = pointFor(series, i)
                    val mid = Offset((prev.x + curr.x) / 2, (prev.y + curr.y) / 2)
                    quadraticTo(prev.x, prev.y, mid.x, mid.y)
                }
                lineTo(pointFor(series, series.lastIndex).x, pointFor(series, series.lastIndex).y)
            }
            drawPath(path, color, style = Stroke(width = 3f, cap = Stroke.DefaultCap))
        }

        drawSmoothed(series2, Color.Yellow)
        drawSmoothed(series1, Color.Blue)
    }
}

@Composable
private fun XAxisLabels(labels: List<String>, chartPadding: Dp) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = chartPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        labels.forEach { day ->
            Text(day, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@FaPreview
@Composable
fun LineChartCardPreview() {
    PreviewSurface {
        LineChartCard()
    }
}
