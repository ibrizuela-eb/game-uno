package repos

import domain.Deck
import java.util.UUID

class DeckRepo {
    private val decks = mutableListOf<Deck>()

    fun save(deck: Deck) {
        decks.add(deck)
    }

    fun findById(id: UUID): Deck? {
        return decks.firstOrNull { it.id == id }
    }

    companion object {
        var deckRepo: DeckRepo? = null

        fun getInstance(): DeckRepo {
            if (deckRepo == null) {
                deckRepo = DeckRepo()
            }
            return deckRepo!!
        }
    }
}
