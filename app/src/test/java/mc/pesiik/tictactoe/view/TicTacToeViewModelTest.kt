package mc.pesiik.tictactoe.view

import io.mockk.every
import io.mockk.mockk
import mc.pesiik.tictactoe.domain.Grid
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.interactor.GridInteractor
import mc.pesiik.tictactoe.ui.TicTacToeScreen
import org.junit.Assert
import org.junit.Test

class TicTacToeViewModelTest {

    private val gridInteractor: GridInteractor = mockk()
    private val ticTacToeMapper: TicTacToeMapper = mockk()
    private lateinit var viewModel: TicTacToeViewModel

    @Test
    fun `WHEN init view model THEN state is correct`() {
        val resetGrid: Grid = mockk()
        val expected : TicTacToeState = mockk()
        every { gridInteractor.reset() } returns resetGrid
        every { ticTacToeMapper.mapToState(resetGrid) } returns expected

        viewModel = TicTacToeViewModel(
            gridInteractor = gridInteractor,
            ticTacToeMapper = ticTacToeMapper
        )
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `WHEN reset event THEN state is reset`() {
        val resetGrid: Grid = mockk()
        val expected: TicTacToeState = mockk()
        every { gridInteractor.reset() } returns resetGrid
        every { ticTacToeMapper.mapToState(resetGrid) } returns expected

        viewModel = TicTacToeViewModel(
            gridInteractor = gridInteractor,
            ticTacToeMapper = ticTacToeMapper
        )
        viewModel.onEvent(TicTacToeScreenEvent.Reset)
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `WHEN cell tap events THEN grid is updated zero after cross`() {
        val updatedAfterCrossGrid: Grid = mockk()
        val updatedAfterZeroGrid: Grid = mockk()
        val afterCrossState = TicTacToeState(
            currentPlayer = Player.ZERO,
        )
        val afterZeroState = TicTacToeState(
            currentPlayer = Player.CROSS,
        )

        val resetGrid: Grid = mockk()
        every { gridInteractor.reset() } returns resetGrid
        every { gridInteractor.cross(row = 0, col = 0) } returns updatedAfterCrossGrid
        every { gridInteractor.zero(row = 0, col = 1) } returns updatedAfterZeroGrid
        every { ticTacToeMapper.mapToState(resetGrid) } returns TicTacToeState()
        every { ticTacToeMapper.mapToState(updatedAfterCrossGrid) } returns afterCrossState
        every { ticTacToeMapper.mapToState(updatedAfterZeroGrid) } returns afterZeroState

        viewModel = TicTacToeViewModel(
            gridInteractor = gridInteractor,
            ticTacToeMapper = ticTacToeMapper
        )

        viewModel.onEvent(TicTacToeScreenEvent.CellTap(row = 0, col = 0))
        Assert.assertEquals(afterCrossState, viewModel.state.value)
        viewModel.onEvent(TicTacToeScreenEvent.CellTap(row = 0, col = 1))
        Assert.assertEquals(afterZeroState, viewModel.state.value)
    }
}