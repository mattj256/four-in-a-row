package com.dreamsmadevisible.games.fourinarow

class Board() {
    val boardString : String

    init {
        val row = "-".repeat(BOARD_WIDTH)
        boardString = row + ("/" + row).repeat(BOARD_HEIGHT - 1)
    }

    fun getDebugBoardString() : String = boardString
}