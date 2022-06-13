package domain

import java.util.UUID

class Play(val gameId: UUID = UUID.randomUUID()) {
    val isActive: Boolean = false
    val cardsQueue: MutableList<Card> = mutableListOf()

    fun cardsQueue(): List<Card> = cardsQueue.toList()

    // La jugada deberia conocer al game y el game la relaciona con el deck
    fun validate(card: Card, deck: Deck) {
        deck.validateCard(card)
    }

    fun playCard(card: Card) {
        if (isActive) {
            // validateCard
            cardsQueue.add(card)
        }
    }
}
