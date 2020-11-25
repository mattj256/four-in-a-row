package com.dreamsmadevisible.games.fourinarow

typealias Move = Int

class Board {

    private val boardString : String

    companion object {
        private const val EMPTY_SQUARE_CHAR = "-"
        private const val COLUMN_DELIMITER = "/"
        private val col = EMPTY_SQUARE_CHAR.repeat(BOARD_HEIGHT)
        private val emptyBoardString = col + (COLUMN_DELIMITER + col).repeat(BOARD_WIDTH - 1)
        //
        private const val REGEX_SKIP_FULL_COLUMN = ".{" + BOARD_HEIGHT + "}"
        private const val REGEX_SKIP_COLUMN_MINUS_ONE = ".{" + (BOARD_HEIGHT - 1) + "}"
        private const val REGEX_SKIP_COLUMN_PLUS_ONE = ".{" + (BOARD_HEIGHT + 1) + "}"
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
        if (isWon()) {
            throw GameException(GameException.ALREADY_WON)
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

    fun isWon(): Boolean {
        // TODO: move to companion class
        // TODO: store Regex objects, not Strings.
        // TODO: check only for one player?
        val regexes = listOf<String>(
                // four-in-a-row vertically
                "XXXX",
                "OOOO",
                // four-in-a-row horizontally
                "X" + REGEX_SKIP_FULL_COLUMN + "X" + REGEX_SKIP_FULL_COLUMN + "X" + REGEX_SKIP_FULL_COLUMN + "X",
                "O" + REGEX_SKIP_FULL_COLUMN + "O" + REGEX_SKIP_FULL_COLUMN + "O" + REGEX_SKIP_FULL_COLUMN + "O",
                // four-in-a-row diagonally up-right
                "X" + REGEX_SKIP_COLUMN_MINUS_ONE + "X" + REGEX_SKIP_COLUMN_MINUS_ONE + "X" + REGEX_SKIP_COLUMN_MINUS_ONE + "X",
                "O" + REGEX_SKIP_COLUMN_MINUS_ONE + "O" + REGEX_SKIP_COLUMN_MINUS_ONE + "O" + REGEX_SKIP_COLUMN_MINUS_ONE + "O",
                // four-in-a-row diagonally up-right
                "X" + REGEX_SKIP_COLUMN_PLUS_ONE + "X" + REGEX_SKIP_COLUMN_PLUS_ONE + "X" + REGEX_SKIP_COLUMN_PLUS_ONE + "X",
                "O" + REGEX_SKIP_COLUMN_PLUS_ONE + "O" + REGEX_SKIP_COLUMN_PLUS_ONE + "O" + REGEX_SKIP_COLUMN_PLUS_ONE + "O"
        )
        return regexes
                .map { x -> x.toRegex() }
                .any { regex -> regex.containsMatchIn(boardString) }
    }

    fun getLegalMoves(): List<Move> {
        val cols = boardString.split(COLUMN_DELIMITER).toList()
        return (0 until BOARD_WIDTH)
                .filter { cols[it].startsWith(EMPTY_SQUARE_CHAR) }
                .toList()
    }

    private fun toPlayer(moveNumber: Int): Player =
        when {
            moveNumber % 2 == 0 -> Player.X
            else -> Player.O
        }
}