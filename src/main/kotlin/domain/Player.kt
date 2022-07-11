package domain

import java.util.UUID

class Player(
    val id: UUID = UUID.randomUUID(),
    private val handCards: MutableList<String> = mutableListOf()
) {

    fun handCards(): MutableList<String> = handCards.toMutableList()

    companion object {
        fun create(): Player {
            return Player()
        }
    }

    fun addCards(cards: List<String>) {
        handCards.addAll(cards)
    }

    fun getCard(cardRepresentation: String): Card {
        if (cardRepresentation !in handCards) throw Error("The player does not have that card")
        return GAME_CARDS[cardRepresentation]!!
    }

    fun removeCard(cardRepresentation: String) {
        handCards.remove(cardRepresentation)
    }
}
