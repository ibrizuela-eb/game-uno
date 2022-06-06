package domain

import factories.DeckFactory
import factories.PlayerFactory
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import java.util.*

class CardTest : StringSpec({
    "should receive a card object given the card value" {
        val cardPlusTwo = GAME_CARDS["+2R"]
        cardPlusTwo should beInstanceOf<ColorCard>()
    }

    "action should add 2 cards from the stack to a player" {
        val cardPlusTwo = GAME_CARDS["+2R"]
        val deck = DeckFactory {
            cards = mutableListOf<String>("1Y", "4G", "6R")
        }
        val myId = UUID.randomUUID()
        val playerTarget = PlayerFactory {
            id = myId
        }
        cardPlusTwo!!.action.apply(
            deck = deck,
            targetPlayer = playerTarget
        )
        playerTarget.handCards() shouldBe mutableListOf("4G", "6R")
    }
})
