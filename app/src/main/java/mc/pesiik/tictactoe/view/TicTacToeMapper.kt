package mc.pesiik.tictactoe.view

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import javax.inject.Inject

class TicTacToeMapper @Inject constructor() {

    fun mapToState(grid: Grid): TicTacToeState {
        return TicTacToeState(
            rows = cellsToView(grid.cells),
            currentPlayer = grid.currentPlayer
        )
    }

    private fun cellsToView(cells: List<List<Cell>>): List<List<TicTacToeState.CellView>> {
        return cells.flatMap { row ->
            listOf(
                row.mapIndexed { colIndex, cell ->
                    TicTacToeState.CellView(
                        cell = cell,
                        col = colIndex
                    )
                }
            )
        }
    }
}