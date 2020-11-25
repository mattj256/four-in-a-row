package com.dreamsmadevisible.games.fourinarow

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import kotlin.test.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

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
    fun negativeMove_columnFull(row: Int, player: Player) {
        var board = Board()
        // TODO: fix this test
        repeat(BOARD_HEIGHT + 1) {
            board = board.move(row, player)
            println(board.getDebugBoardString())
        }
        // board = board.move(row, player)
    }

}
