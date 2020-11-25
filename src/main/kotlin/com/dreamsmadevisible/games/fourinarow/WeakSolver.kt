package com.dreamsmadevisible.games.fourinarow

import java.lang.UnsupportedOperationException

class WeakSolver {

    fun solve(moveSequence: String): Player {
        val board = Board().move(moveSequence)
        val player = when {
            moveSequence.length % 2 == 0 -> Player.X
            else -> Player.O
        }
        return solve(board, player)
    }

    fun solve(board: Board, player: Player): Player {
        val otherPlayer = player.other()
        if (board.isWon()) {
            return otherPlayer
        }
        val legalMoves: List<Move> = board.getLegalMoves()
        if (legalMoves.any { board.move(it, player).isWon() }) {
            return player
        }
        // TODO
        throw UnsupportedOperationException()
    }
}