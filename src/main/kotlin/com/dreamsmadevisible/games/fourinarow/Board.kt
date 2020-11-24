package com.dreamsmadevisible.games.fourinarow

class Board() {
    val boardString : String

    init {
        val col = "-".repeat(BOARD_HEIGHT)
        boardString = col + ("/" + col).repeat(BOARD_WIDTH - 1)
    }

    fun getDebugBoardString() : String = boardString

    /*
    fun move(col: Int, player: Player) : Board {
        boardString.su

        return this
    }

    private fun getColString(col: Int): String =
            boardString
                    .split("/")
                    .map { x -> x.get(col)}
                    .toString()
     */
}