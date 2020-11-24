package com.dreamsmadevisible.games.fourinarow

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import kotlin.test.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
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
    fun move(expected: String, row: Int, player: Player) {
        val board = Board().move(row, player)
        assertEquals(expected, board.getDebugBoardString())
    }

}
