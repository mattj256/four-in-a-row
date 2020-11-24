package com.dreamsmadevisible.games.fourinarow

enum class Player(val char: Char) {
    X('X') {
        override fun other() = O
    },
    O('O') {
        override fun other() = X
    };

    abstract fun other() : Player
}
