package com.dreamsmadevisible.games.fourinarow

class Board {
    val board : Array<Array<Square>> = Array(BOARD_WIDTH) {Array(BOARD_HEIGHT) { Square.EMPTY } }

    fun get(col: Int, row: Int) : Square = board[col][row]

    private fun set(col: Int, row: Int, value: Square) : Unit {
        board[col][row] = value
    }
}