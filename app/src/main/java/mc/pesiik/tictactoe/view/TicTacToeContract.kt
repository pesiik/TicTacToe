package mc.pesiik.tictactoe.view

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Player

data class TicTacToeState(
    val rows: List<List<CellView>> = emptyList(),
    val currentPlayer: Player = Player.CROSS,
) {

    data class CellView(
        val cell: Cell,
        val col: Int,
    )
}

sealed interface TicTacToeScreenEvent {
    data class CellTap(val row: Int, val col: Int) : TicTacToeScreenEvent
    data object Reset : TicTacToeScreenEvent
}