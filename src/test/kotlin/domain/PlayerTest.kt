package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "test should create a player object with an empty hand" {
        val player = Player.create()
        player.handCards.isEmpty() shouldBe true
    }
})
