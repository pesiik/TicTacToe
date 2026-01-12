package mc.pesiik.tictactoe.view

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import javax.inject.Inject

class TicTacToeMapper @Inject constructor() {

    fun mapToState(grid: Grid): TicTacToeState {
        return TicTacToeState(
            cells = cellsToView(grid.cells),
            currentPlayer = grid.currentPlayer
        )
    }

    private fun cellsToView(cells: List<List<Cell>>): List<TicTacToeState.CellView> {
        return cells.flatMapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, cell ->
                TicTacToeState.CellView(
                    cell = cell,
                    row = rowIndex,
                    col = colIndex
                )
            }
        }
    }
}