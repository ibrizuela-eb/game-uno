package domain

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    fun testShouldCreateAPlayerObject() {
        val player = Player.create()
        assertTrue(player is Player)
    }
}
