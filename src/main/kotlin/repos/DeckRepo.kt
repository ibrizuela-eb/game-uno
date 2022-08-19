package repos

import domain.Deck
import org.springframework.stereotype.Repository
import java.util.UUID

interface DeckRepo {
    fun save(deck: Deck)
    fun findById(id: UUID): Deck?
}

@Repository
object DeckRepoInMemory : DeckRepo {
    private val decks = mutableListOf<Deck>()
    override fun save(deck: Deck) {
        decks.add(deck)
    }

    override fun findById(id: UUID): Deck? {
        return decks.firstOrNull { it.id == id }
    }
}

// class DeckRepo {
//    private val decks = mutableListOf<Deck>()
//
//    fun save(deck: Deck) {
//        decks.add(deck)
//    }
//
//    fun findById(id: UUID): Deck? {
//        return decks.firstOrNull { it.id == id }
//    }
//
//    companion object {
//        var deckRepo: DeckRepo? = null
//
//        fun getInstance(): DeckRepo {
//            if (deckRepo == null) {
//                deckRepo = DeckRepo()
//            }
//            return deckRepo!!
//        }
//    }
// }