package com.dreamsmadevisible.games.fourinarow

class Board {

    val boardString : String

    companion object {
        val EMPTY_SQUARE_CHAR = "-"
        val COLUMN_DELIMITER = "/"
        val col = EMPTY_SQUARE_CHAR.repeat(BOARD_HEIGHT)
        val emptyBoardString = col + ("/" + col).repeat(BOARD_WIDTH - 1)
    }

    constructor(): this(emptyBoardString)

    private constructor(_boardString: String) {
        boardString = _boardString
    }

    fun getDebugBoardString() : String = boardString

    fun move(row: Int, player: Player) : Board {
        val cols = boardString.split(COLUMN_DELIMITER).toMutableList()
        val oldCol = cols[row]
        val index = oldCol.lastIndexOf(EMPTY_SQUARE_CHAR)
        val stringBuilder = StringBuilder(oldCol)
        stringBuilder.setCharAt(index, player.char)
        val newCol = stringBuilder.toString()
        cols.set(row, newCol)
        val newBoardString = cols.joinToString(separator = COLUMN_DELIMITER)
        return Board(newBoardString)
    }
}