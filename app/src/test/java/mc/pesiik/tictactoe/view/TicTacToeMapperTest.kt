package mc.pesiik.tictactoe.view

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.domain.Status
import org.junit.Assert
import org.junit.Test

class TicTacToeMapperTest {

    private val mapper = TicTacToeMapper()

    @Test
    fun `WHEN map domain THEN return correct state`() {
        val grid = Grid(
            cells = listOf(
                listOf(Cell.NONE, Cell.CROSS, Cell.ZERO),
                listOf(Cell.ZERO, Cell.CROSS, Cell.NONE),
                listOf(Cell.CROSS, Cell.NONE, Cell.ZERO)
            ),
            currentPlayer = Player.CROSS,
            status = Status.Draw
        )
        val expectedState = TicTacToeState(
            rows = listOf(
                listOf(
                    TicTacToeState.CellView(Cell.NONE, col = 0),
                    TicTacToeState.CellView(Cell.CROSS, col = 1),
                    TicTacToeState.CellView(Cell.ZERO, col = 2)
                ),
                listOf(
                    TicTacToeState.CellView(Cell.ZERO, col = 0),
                    TicTacToeState.CellView(Cell.CROSS, col = 1),
                    TicTacToeState.CellView(Cell.NONE, col = 2)
                ),
                listOf(
                    TicTacToeState.CellView(Cell.CROSS, col = 0),
                    TicTacToeState.CellView(Cell.NONE, col = 1),
                    TicTacToeState.CellView(Cell.ZERO, col = 2)
                )
            ),
            currentPlayer = Player.CROSS
        )
        val actualState = mapper.mapToState(grid)
        Assert.assertEquals(expectedState, actualState)
    }
}