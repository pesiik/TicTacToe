package mc.pesiik.tictactoe.view

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.domain.Status

data class TicTacToeState(
    val rows: List<List<CellView>> = emptyList(),
    val currentPlayer: Player = Player.CROSS,
    val status: Status = Status.Active,
) {

    data class CellView(
        val cell: Cell,
        val col: Int,
        val isWinner: Boolean = false,
    )
}

sealed interface TicTacToeScreenEvent {
    data class CellTap(val row: Int, val col: Int) : TicTacToeScreenEvent
    data object Reset : TicTacToeScreenEvent
}