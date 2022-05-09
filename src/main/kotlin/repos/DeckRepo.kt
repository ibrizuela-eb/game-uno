package repos

import domain.Deck
import java.util.*

class DeckRepo {
    private val decks = mutableListOf<Deck>()

    fun save(deck: Deck) {
        decks.add(deck)
    }

    fun findById(id: UUID): Deck? {
        return decks.firstOrNull { it.id == id }
    }
}
