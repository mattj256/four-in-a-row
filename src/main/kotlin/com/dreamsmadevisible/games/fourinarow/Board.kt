package com.dreamsmadevisible.games.fourinarow

class Board {

    private val boardString : String

    companion object {
        private const val EMPTY_SQUARE_CHAR = "-"
        private const val COLUMN_DELIMITER = "/"
        private val col = EMPTY_SQUARE_CHAR.repeat(BOARD_HEIGHT)
        private val emptyBoardString = col + (COLUMN_DELIMITER + col).repeat(BOARD_WIDTH - 1)
    }

    constructor(): this(emptyBoardString)

    private constructor(_boardString: String) {
        boardString = _boardString
    }

    fun getDebugBoardString() : String = boardString

    fun move(moveSequence: String): Board =
            moveSequence
                    .toCharArray()
                    .foldIndexed(Board(), {
                        index, acc, char ->  acc.move(char - '0', toPlayer(index))
                    })

    fun move(colIndex: Int, player: Player) : Board {
        if (colIndex < 0 || colIndex >= BOARD_WIDTH) {
            throw GameException(GameException.INVALID_COL + colIndex)
        }
        val cols = boardString.split(COLUMN_DELIMITER).toMutableList()
        val oldCol = cols[colIndex]
        val index = oldCol.lastIndexOf(EMPTY_SQUARE_CHAR)
        if (index < 0) {
            throw GameException(GameException.COLUMN_FULL + colIndex)
        }
        val stringBuilder = StringBuilder(oldCol)
        stringBuilder.setCharAt(index, player.char)
        val newCol = stringBuilder.toString()
        cols[colIndex] = newCol
        val newBoardString = cols.joinToString(separator = COLUMN_DELIMITER)
        return Board(newBoardString)
    }

    private fun toPlayer(moveNumber: Int): Player =
        when {
            moveNumber % 2 == 0 -> Player.X
            else -> Player.O
        }
}