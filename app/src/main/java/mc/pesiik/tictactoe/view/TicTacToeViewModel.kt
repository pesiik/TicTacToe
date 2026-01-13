package mc.pesiik.tictactoe.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import mc.pesiik.tictactoe.domain.Player
import mc.pesiik.tictactoe.interactor.GridInteractor
import javax.inject.Inject

@HiltViewModel
class TicTacToeViewModel @Inject constructor(
    private val gridInteractor: GridInteractor,
    private val ticTacToeMapper: TicTacToeMapper
) : ViewModel() {

    private val _state = MutableStateFlow(TicTacToeState())
    val state: StateFlow<TicTacToeState> = _state.asStateFlow()

    init {
        reset()
    }

    // All in the main thread. The calculations are simple and quick.
    fun onEvent(event: TicTacToeScreenEvent) {
        when (event) {
            TicTacToeScreenEvent.Reset -> reset()
            is TicTacToeScreenEvent.CellTap -> updateGrid(cellTapEvent = event)
        }
    }

    private fun reset() {
        val resetGrid = gridInteractor.reset()
        val viewState = ticTacToeMapper.mapToState(resetGrid)
        _state.value = viewState
    }

    // No error handling because we don't need it.
    private fun updateGrid(cellTapEvent: TicTacToeScreenEvent.CellTap) {
        val currentPlayer = state.value.currentPlayer
        val grid = when (currentPlayer) {
            Player.CROSS -> gridInteractor.cross(row = cellTapEvent.row, col = cellTapEvent.col)
            Player.ZERO -> gridInteractor.zero(row = cellTapEvent.row, col = cellTapEvent.col)
        }
        val viewState = ticTacToeMapper.mapToState(grid)
        _state.value = viewState
    }
}