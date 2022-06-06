package factories

import domain.Constants
import domain.Deck
import java.util.UUID

class DeckFactory {
    var id: UUID = UUID.randomUUID()
    var cards: MutableList<String> = Constants.DEFAULT_DECK_CARDS.toMutableList()
    val shouldInitializeCards: Boolean = true

    companion object {
        operator fun invoke(init: DeckFactory.() -> Unit): Deck {
            val deckFactory = DeckFactory()
            deckFactory.init()
            val deck = Deck(deckFactory.id, deckFactory.cards)
            if (deckFactory.shouldInitializeCards) {
                deck.initializeStacks()
            }
            return deck
        }
    }
}
