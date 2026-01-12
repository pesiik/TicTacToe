package mc.pesiik.tictactoe.interactor

import mc.pesiik.tictactoe.domain.Grid

interface GridInteractor {
    fun cross(row: Int, col: Int): Grid
    fun zero(row: Int, col: Int): Grid
    fun reset(): Grid
}