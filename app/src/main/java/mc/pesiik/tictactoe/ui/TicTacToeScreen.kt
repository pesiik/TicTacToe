package mc.pesiik.tictactoe.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import mc.pesiik.tictactoe.ui.components.TicTacToeCell
import mc.pesiik.tictactoe.view.TicTacToeScreenEvent
import mc.pesiik.tictactoe.view.TicTacToeState
import mc.pesiik.tictactoe.view.TicTacToeViewModel

@Composable
fun TicTacToeScreen(
    viewModel: TicTacToeViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LaunchedEffect(TicTacToeScreenEvent.Reset) {
            viewModel.onEvent(TicTacToeScreenEvent.Reset)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .drawBehind {
                    drawGridBorder(times = state.value.rows.size)
                }
        ) {
            TicTacToeGrid(state = state.value) { row, col ->
                viewModel.onEvent(TicTacToeScreenEvent.CellTap(row, col))
            }
        }
    }
}

@Composable
private fun TicTacToeGrid(state: TicTacToeState, onCellTap: (row: Int, col: Int) -> Unit) {
    val times = state.rows.size
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(times) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                repeat(times) { col ->
                    TicTacToeCell(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .clickable {
                                onCellTap(row, col)
                            },
                        cellView = state.rows[row][col],
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawGridBorder(times: Int) {
    repeat(times) { index ->
        if (index != 0) {
            val width = size.width / times * index
            val height = size.height / times * index
            drawLine(
                color = Color.Green,
                start = Offset(
                    x = width,
                    y = 0f
                ),
                end = Offset(
                    x = width,
                    y = size.height
                )
            )
            drawLine(
                color = Color.Green,
                start = Offset(
                    x = 0f,
                    y = height
                ),
                end = Offset(
                    x = size.width,
                    y = height
                )
            )
        }
    }
}