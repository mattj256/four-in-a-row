package com.dreamsmadevisible.games.fourinarow

import java.lang.UnsupportedOperationException

class WeakSolver {

    companion object {
        private const val MAX_PLY = 2
    }

    fun solve(moveSequence: String): Player? {
        val board = Board().move(moveSequence)
        val player = when {
            moveSequence.length % 2 == 0 -> Player.X
            else -> Player.O
        }
        return solve(board, player)
    }

    private fun solve(board: Board, player: Player): Player? {
        return solve(board, player, MAX_PLY)
    }

    private fun solve(board: Board, player: Player, plyRemaining: Int): Player? {
        val otherPlayer = player.other()
        when {
            board.isWon() -> return otherPlayer
            plyRemaining <= 0 -> return null
        }

        val results: List<Player?> = board.getLegalMoves()
                .map { board.move(it, player) }
                .map { solve(it, otherPlayer, plyRemaining - 1) }
        return when {
            results.contains(player) -> player
            results.all { it == otherPlayer } -> otherPlayer
            else -> null
        }
    }
}