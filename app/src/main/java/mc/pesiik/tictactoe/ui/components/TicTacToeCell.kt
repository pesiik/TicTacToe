package mc.pesiik.tictactoe.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.view.TicTacToeState

@Composable
fun TicTacToeCell(
    modifier: Modifier = Modifier,
    cellView: TicTacToeState.CellView,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .drawWithCache {
                when (cellView.cell) {
                    Cell.NONE -> clear()
                    Cell.CROSS -> drawCross()
                    Cell.ZERO -> drawZero()
                }
            }
    )
}

private fun CacheDrawScope.drawCross(): DrawResult {
    return onDrawBehind {
        val strokeWidth = size.width * 0.1f
        drawLine(
            color = Color.Red,
            strokeWidth = strokeWidth,
            start = Offset(0f + strokeWidth / 2, 0f + strokeWidth / 2),
            end = Offset(size.width - strokeWidth / 2, size.height - strokeWidth / 2)
        )
        drawLine(
            color = Color.Red,
            strokeWidth = strokeWidth,
            start = Offset(size.width - strokeWidth / 2, 0f + strokeWidth / 2),
            end = Offset(0f + strokeWidth / 2, size.height - strokeWidth / 2)
        )
    }
}

private fun CacheDrawScope.drawZero(): DrawResult {
    return onDrawBehind {
        val strokeWidth = size.width * 0.1f
        drawCircle(
            color = Color.Blue,
            radius = (size.minDimension - strokeWidth) / 2,
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
        )
    }
}

private fun CacheDrawScope.clear(): DrawResult {
    return onDrawBehind {
        // Do nothing, effectively clearing the cell
    }
}