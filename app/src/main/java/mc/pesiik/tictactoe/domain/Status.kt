package mc.pesiik.tictactoe.domain

sealed interface Status {
    data object Active : Status
    data class Winner(val player: Player) : Status
}