package mc.pesiik.tictactoe.view

import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Status
import javax.inject.Inject

class TicTacToeMapper @Inject constructor() {

    fun mapToState(grid: Grid): TicTacToeState {
        val winnerPositions = when (val status = grid.status) {
            is Status.Winner -> status.winnerLine.toSet()
            else -> emptySet()
        }

        val rows = grid.cells.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, cell ->
                TicTacToeState.CellView(
                    cell = cell,
                    col = colIndex,
                    isWinner = Pair(rowIndex, colIndex) in winnerPositions
                )
            }
        }

        return TicTacToeState(
            rows = rows,
            currentPlayer = grid.currentPlayer,
            status = grid.status
        )
    }
}