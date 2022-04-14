package domain

import java.util.UUID

class Deck {
    private val cards: MutableList<String> = mutableListOf("2R", "6R", "2Y", "3Y", "2B", "5B", "8G", "9G", "+2", "+4")

    fun shuffleCards(){
        cards.shuffle()
    }

    fun dealCards(playerOneId: UUID, playerTwoId: UUID): Map<UUID, List<String>>{
        val cardsPlayerOne = cards.slice(IntRange(0,2))
        cards.removeAll(cardsPlayerOne)
        val cardsPlayerTwo = cards.slice(IntRange(0,2))
        cards.removeAll(cardsPlayerTwo)
        return mapOf(
            Pair(playerOneId, cardsPlayerOne),
            Pair(playerTwoId, cardsPlayerTwo)
        )
    }
}