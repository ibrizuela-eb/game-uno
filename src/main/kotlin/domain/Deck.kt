package domain

import domain.Constants.DEFAULT_DECK_CARDS
import domain.Constants.MAX_HAND_CARDS
import java.util.UUID

class Deck(
    val id: UUID = UUID.randomUUID(),
    private val cards: MutableList<String> = DEFAULT_DECK_CARDS.toMutableList()
) {
    private val liveStack: MutableList<String> = mutableListOf()
    private val takeStack: MutableList<String> = mutableListOf()

    fun shuffleCards() {
        cards.shuffle()
    }

    fun dealCards(playerOneId: UUID, playerTwoId: UUID): Map<UUID, List<String>> {
        val cardsPlayerOne = cards.slice(0 until MAX_HAND_CARDS)
        cards.removeAll(cardsPlayerOne)
        val cardsPlayerTwo = cards.slice(0 until MAX_HAND_CARDS)
        cards.removeAll(cardsPlayerTwo)
        return mapOf(
            Pair(playerOneId, cardsPlayerOne),
            Pair(playerTwoId, cardsPlayerTwo)
        )
    }

    fun initializeStacks() {
        liveStack.add(cards.removeFirst())
        takeStack.addAll(cards)
        cards.clear()
    }

    fun getLiveCard(): String {
        return liveStack.last()
    }

    fun getCard(): String {
        var takeCard = takeStack.removeFirstOrNull()
        if (takeCard == null) {
            takeStack.addAll(liveStack.slice(1 until liveStack.size))
            takeStack.shuffle()
            takeCard = takeStack.removeFirstOrNull()
        }
        return takeCard!!
    }

    fun getCards(n: Int = 1): List<String> {
//        val cards = mutableListOf<String>()
//        for (i in 0..n) {
//            var takeCard = takeStack.removeFirstOrNull()
//            if (takeCard == null) {
//                takeStack.addAll(liveStack.slice(0 until liveStack.size))
//                takeStack.shuffle()
//                takeCard = takeStack.removeFirstOrNull()
//            }
//            cards.add(takeCard)
//            // var liveCard = takeStack.removeFirstOrNull() ?: run {...}
//        }
        return (0 until n).map { getCard() }
    }
}
