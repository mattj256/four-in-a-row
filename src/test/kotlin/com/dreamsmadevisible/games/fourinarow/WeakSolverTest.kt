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
           "334455"
   )
   fun positiveSolve_winPlayerOne(moveSequence: String) {
      val actual = WeakSolver().solve(moveSequence)
      assertEquals(Player.X, actual)
   }
}
