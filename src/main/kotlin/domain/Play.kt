package domain

import java.util.UUID

class Play(val gameId: UUID = UUID.randomUUID()) {
    val isActive: Boolean = false
    val cardsQueue: MutableList<Card> = mutableListOf()

    fun cardsQueue(): List<Card> = cardsQueue.toList()

    fun addCardToQueue(card: Card) {
        if (isActive) {
            cardsQueue.add(card)
        }
    }
}
