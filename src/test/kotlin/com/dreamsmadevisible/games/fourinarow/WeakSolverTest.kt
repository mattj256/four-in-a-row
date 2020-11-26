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
class WeakSolverTest {

   @Test
   @Parameters(
           // win in 1 ply (same player)
           "334455|X",
           "0334455|O",
           // win in 2 ply (other player)
           "33445|X",
           "033445|O"
   )
   fun positiveSolve_winPlayerOne(moveSequence: String, player: Player) {
      val actual: Player? = WeakSolver().solve(moveSequence)
      assertEquals(player, actual)
   }
}
