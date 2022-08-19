package domain

import java.util.UUID

data class PlayerResponse(
    val id: String,
    val handCards: MutableList<String>
)

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

    fun toResponse(): PlayerResponse {
        return PlayerResponse(
            id.toString(),
            handCards
        )
    }

    override fun toString(): String {
        return id.toString()
    }
}
