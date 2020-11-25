package com.dreamsmadevisible.games.fourinarow

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import kotlin.test.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(value = JUnitParamsRunner::class)
class BoardTest {

    @Test
    fun getBoardString_empty() {
        assertEquals("------/------/------/------/------/------/------", Board().getDebugBoardString())
    }

    @Test
    @Parameters(
            "-----X/------/------/------/------/------/------|0|X",
            "------/-----X/------/------/------/------/------|1|X",
            "------/------/-----X/------/------/------/------|2|X",
            "------/------/------/-----X/------/------/------|3|X",
            "------/------/------/------/-----X/------/------|4|X",
            "------/------/------/------/------/-----X/------|5|X",
            "------/------/------/------/------/------/-----X|6|X",
            "-----O/------/------/------/------/------/------|0|O",
            "------/-----O/------/------/------/------/------|1|O",
            "------/------/-----O/------/------/------/------|2|O",
            "------/------/------/-----O/------/------/------|3|O",
            "------/------/------/------/-----O/------/------|4|O",
            "------/------/------/------/------/-----O/------|5|O",
            "------/------/------/------/------/------/-----O|6|O"
    )
    fun positiveMove(expected: String, row: Int, player: Player) {
        val board = Board().move(row, player)
        assertEquals(expected, board.getDebugBoardString())
    }

    @Test
    @Parameters(
            "0|X",
            "1|X",
            "2|X",
            "3|X",
            "4|X",
            "5|X",
            "6|X",
            "0|O",
            "1|O",
            "2|O",
            "3|O",
            "4|O",
            "5|O",
            "6|O"
    )
    fun negativeMove_columnFull(col: Int, player: Player) {
        var board = Board()
        repeat(BOARD_HEIGHT) {
            board = board.move(col, player)
        }
        assertThrows<GameException> {
            board = board.move(col, player)
        }
    }

    @Test
    @Parameters(
            "-1|X",
            "7|X",
            "-1|O",
            "7|O"
    )
    fun negativeMove_invalidColumn(col: Int, player: Player) {
        assertThrows<GameException> {
            Board().move(col, player)
        }
    }

    @Test
    @Parameters(
            "-----X/------/------/------/------/------/------|0",
            "------/-----X/------/------/------/------/------|1",
            "------/------/-----X/------/------/------/------|2",
            "------/------/------/-----X/------/------/------|3",
            "------/------/------/------/-----X/------/------|4",
            "------/------/------/------/------/-----X/------|5",
            "------/------/------/------/------/------/-----X|6",
            "------/------/-----O/----OX/-----X/------/------|3342"
    )
    fun positiveMove_moveSequence(expected: String, moveSequence: String) {
        val actual = Board().move(moveSequence).getDebugBoardString()
        assertEquals(expected, actual)
    }

    @Test
    @Parameters(
            "0000000",
            "1111111",
            "2222222",
            "3333333",
            "4444444",
            "5555555",
            "6666666"
    )
    fun negativeMove_moveSequence_columnFull(moveSequence: String) {
        // All moves except the last move should be legal.
        assertDoesNotThrow {
            Board().move(moveSequenceMinusLastMove(moveSequence))
        }
        // The last move is illegal.
        assertThrows<GameException> {
            Board().move(moveSequence)
        }
    }

    // TODO: add negative tests for moveSequence that continues past the end of the game.
    // TODO: add positive tests for winning by diagonal

    @Test
    @Parameters(
            // X win - vertical
            "0101010",
            "1212121",
            // X win - horizontal
            "0011223",
            "3344556",
            // X win - up-right diagonal
            "01122323433",
            "23344545655",
            // X win - up-left diagonal
            "65544343233",
            "43322121011",
            // O win - vertical
            "01010121",
            "56565606",
            // O win - horizontal
            "0011224343",
            "3344550606",
            // O win - up-right diagonal
            "601122323433",
            "023344545655",
            // O win - up-left diagonal
            "065544343233",
            "643322121011"
    )
    fun positiveIsWon(moveSequence: String) {
        assertFalse(actual = Board().move(moveSequenceMinusLastMove(moveSequence)).isWon(), message = "Should not be won: " + getDebugInfo(moveSequence))
        assertTrue(actual = Board().move(moveSequence).isWon(), message = "Should be won: " + getDebugInfo(moveSequence))
    }

    private fun moveSequenceMinusLastMove(moveSequence: String) =
            moveSequence.take(moveSequence.length - 1)

    private fun getDebugInfo(moveSequence: String) =
            Pair<String, String>(moveSequence, Board().move(moveSequence).getDebugBoardString())
}
