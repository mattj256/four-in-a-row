package com.dreamsmadevisible.games.fourinarow

class Board() {
    private val EMPTY_SQUARE_CHAR = "-"

    var boardString : String

    init {
        val col = EMPTY_SQUARE_CHAR.repeat(BOARD_HEIGHT)
        boardString = col + ("/" + col).repeat(BOARD_WIDTH - 1)
    }

    private constructor(_boardString: String): this() {
        boardString = _boardString
    }

    fun getDebugBoardString() : String = boardString

    fun move(row: Int, player: Player) : Board {
        val cols = boardString.split("/").toMutableList()
        val oldCol = cols[row]
        val index = oldCol.lastIndexOf(EMPTY_SQUARE_CHAR)
        val stringBuilder = StringBuilder(oldCol)
        stringBuilder.setCharAt(index, player.char)
        val newCol = stringBuilder.toString()
        cols.set(row, newCol)
        val newBoardString = cols.joinToString(separator = "/")
        return Board(newBoardString)
    }
}