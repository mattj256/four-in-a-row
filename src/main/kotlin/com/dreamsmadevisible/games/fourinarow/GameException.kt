package com.dreamsmadevisible.games.fourinarow

import java.lang.RuntimeException

class GameException(override val message: String) : RuntimeException(message) {

    companion object {
        val COLUMN_FULL = "Column is full: "
        val INVALID_COL = "Invalid column: "
    }
}
