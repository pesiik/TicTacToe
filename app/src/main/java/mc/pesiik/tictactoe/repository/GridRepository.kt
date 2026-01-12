package mc.pesiik.tictactoe.repository

import mc.pesiik.tictactoe.domain.Cell
import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.domain.Status

interface GridRepository {
    fun getGrid(): Grid
    fun updateGrid(cells: List<List<Cell>>, player: Player, status: Status = Status.Active): Grid
}