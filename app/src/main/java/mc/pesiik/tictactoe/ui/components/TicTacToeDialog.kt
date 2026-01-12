package mc.pesiik.tictactoe.ui.components

import androidx.compose.runtime.Composable
import mc.pesiik.tictactoe.domain.Status
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import mc.pesiik.tictactoe.R
import mc.pesiik.tictactoe.domain.Player

@Composable
fun TicTacToeDialog(
    status: Status,
    onReset: () -> Unit
) {
    if (status !is Status.Active) {
        AlertDialog(
            onDismissRequest = { /* Prevent dismissal by clicking outside */ },
            title = {
                Text(
                    text = when (status) {
                        is Status.Winner -> WinnerText(player = status.player)
                        is Status.Draw -> stringResource(id = R.string.tic_tac_toe_screen_draw)
                        else -> ""
                    },
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = onReset
                ) {
                    Text(text = stringResource(id = R.string.tic_tac_toe_screen_play_again))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Composable
private fun WinnerText(player: Player): String {
    return stringResource(
        id = when (player) {
            Player.CROSS -> R.string.tic_tac_toe_screen_player_x_wins
            Player.ZERO -> R.string.tic_tac_toe_screen_player_o_wins
        }
    )
}