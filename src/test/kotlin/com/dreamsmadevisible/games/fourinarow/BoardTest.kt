package com.dreamsmadevisible.games.fourinarow

import kotlin.test.assertEquals
import org.junit.Test
import kotlin.test.assertTrue

class BoardTest {

    @Test
    fun getBoardString_empty() {
        assertEquals("------/------/------/------/------/------/------", Board().getDebugBoardString())
    }

    @Test
    fun move() {
        assertEquals("-----X/------/------/------/------/------/------", Board().move(0, Player.X).getDebugBoardString())
    }

}
