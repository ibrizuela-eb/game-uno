package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.util.UUID

internal class DeckTest {
    @Test
    fun testShouldShuffleDeck() {
        val deck = Deck()
        deck.shuffleCards()
        assertNotEquals(deck, mutableListOf("2R", "6R", "2Y", "3Y", "2B", "5B", "8G", "9G", "+2", "+4"))
    }

    @Test
    fun testShouldDealCardsToPlayers() {
        val deck = Deck()
        val playerOneId = UUID.randomUUID()
        val playerTwoId = UUID.randomUUID()
        val playersHand = deck.dealCards(
            playerOneId = playerOneId,
            playerTwoId = playerTwoId,
        )
        assertEquals(playersHand[playerOneId], listOf("2R", "6R", "2Y"))
        assertEquals(playersHand[playerTwoId], listOf("3Y", "2B", "5B"))
    }
}
