package domain

import factories.DeckFactory
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldNotBeIn
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

class DeckTest : StringSpec({
    "test should shuffle deck" {
        val cards = mutableListOf("2R", "6R", "2Y", "3Y", "2B", "5B", "8G", "9G", "+2", "+4")
        val deck = Deck(cards = cards)
        deck.shuffleCards()
        deck shouldNotBe mutableListOf("2R", "6R", "2Y", "3Y", "2B", "5B", "8G", "9G", "+2", "+4")
    }

    "test should deal cards to players" {
        val cards = mutableListOf(
            "1B", "2B", "3B", "4B", "5B", "6B", "7B", "8B", "9B",
            "1G", "2G", "3G", "4G", "5G", "6G", "7G", "8G", "9G"
        )
        val deck = Deck(cards = cards)
        val playerOneId = UUID.randomUUID()
        val playerTwoId = UUID.randomUUID()
        val playersHand = deck.dealCards(
            playerOneId = playerOneId,
            playerTwoId = playerTwoId,
        )
        playersHand[playerOneId] shouldBe listOf("1B", "2B", "3B", "4B", "5B", "6B", "7B")
        playersHand[playerTwoId] shouldBe listOf("8B", "9B", "1G", "2G", "3G", "4G", "5G")
    }

    "test should put the initial card in the liveStack" {
        val deckMocked = Deck(cards = mutableListOf("2R", "6R", "2Y", "3Y"))
        deckMocked.initializeStacks()
        deckMocked.getLiveCard() shouldBe "2R"
    }

    "test should return a card from takeStack" {
        val deckMocked = Deck(cards = mutableListOf("2R", "6R", "2Y", "3Y"))
        deckMocked.initializeStacks()
        deckMocked.getCard() shouldBe "6R"
        deckMocked.getCard() shouldBe "2Y"
        deckMocked.getCard() shouldBe "3Y"
    }

    "test should always return a card even after taken all cards" {
        val deckMocked = Deck(cards = mutableListOf("2R", "6R"))
        deckMocked.initializeStacks()
        deckMocked.getCard() shouldBe "6R"
        deckMocked.getCard() shouldNotBe null
    }

    "test mock" {
        val mockedDeck = mockk<Deck>()
        every { mockedDeck.getCard() } returns "10G"
        mockedDeck.getCard() shouldBe "10G"
    }

    "test getCards should not return the liveCard" {
        var deckCards = mutableListOf<String>("1Y", "4G", "6R", "3R")
        val deck = DeckFactory { cards = deckCards.toMutableList() }
        val liveCard = deck.getLiveCard()
        val cardsTaken = deck.getCards(n = 3)
        liveCard shouldNotBeIn cardsTaken
    }
})
