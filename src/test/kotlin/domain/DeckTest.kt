package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

class DeckTest : StringSpec({
    "test should shuffle deck" {
        val deck = Deck()
        deck.shuffleCards()
        deck shouldNotBe mutableListOf("2R", "6R", "2Y", "3Y", "2B", "5B", "8G", "9G", "+2", "+4")
    }

    "test should deal cards to players" {
        val deck = Deck()
        val playerOneId = UUID.randomUUID()
        val playerTwoId = UUID.randomUUID()
        val playersHand = deck.dealCards(
            playerOneId = playerOneId,
            playerTwoId = playerTwoId,
        )
        playersHand[playerOneId] shouldBe listOf("2R", "6R", "2Y")
        playersHand[playerTwoId] shouldBe listOf("3Y", "2B", "5B")
    }

    "test should put the initial card in the liveStack" {
        val deckMocked = Deck(mutableListOf("2R", "6R", "2Y", "3Y"))
        deckMocked.initializeStacks()
        deckMocked.getLiveCard() shouldBe "2R"
    }

    "test should return a card from takeStack" {
        val deckMocked = Deck(mutableListOf("2R", "6R", "2Y", "3Y"))
        deckMocked.initializeStacks()
        deckMocked.getCard() shouldBe "6R"
        deckMocked.getCard() shouldBe "2Y"
        deckMocked.getCard() shouldBe "3Y"
    }

    "test should always return a card even after taken all cards" {
        val deckMocked = Deck(mutableListOf("2R", "6R"))
        deckMocked.initializeStacks()
        deckMocked.getCard() shouldBe "6R"
        deckMocked.getCard() shouldNotBe null
    }

    "test mock" {
        val mockedDeck = mockk<Deck>()
        every { mockedDeck.getCard() } returns "10G"
        mockedDeck.getCard() shouldBe "10G"
    }
})
