package mc.pesiik.tictactoe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import mc.pesiik.tictactoe.R
import mc.pesiik.tictactoe.domain.Status
import mc.pesiik.tictactoe.ui.components.TicTacToeDialog
import mc.pesiik.tictactoe.ui.components.TicTacToeGridBox
import mc.pesiik.tictactoe.view.TicTacToeScreenEvent
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
        if (state.value.status != Status.Active) {
            TicTacToeDialog(
                status = state.value.status,
                onReset = {
                    viewModel.onEvent(TicTacToeScreenEvent.Reset)
                }
            )
        }
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Title()
            TicTacToeGridBox(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                state = state.value
            ) { row, col ->
                viewModel.onEvent(TicTacToeScreenEvent.CellTap(row, col))
            }
            ResetButton {
                viewModel.onEvent(TicTacToeScreenEvent.Reset)
            }
        }
    }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(vertical = 32.dp),
        text = stringResource(R.string.tic_tac_toe_screen_title),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun ResetButton(modifier: Modifier = Modifier, onReset: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Cyan,
                        Color.Magenta,
                    )
                )
            )
            .clickable { onReset() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = modifier
                .wrapContentSize()
                .padding(vertical = 16.dp),
            text = stringResource(R.string.tic_tac_toe_screen_reset_button),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}