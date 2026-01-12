package mc.pesiik.tictactoe.domain

data class Grid(
    val cells: List<List<Cell>>,
    val currentPlayer: Player,
    val status: Status
)