package application
import domain.Deck
import domain.Player
import repos.DeckRepo
import repos.PlayerRepo
import java.util.UUID

fun initializeGame(playerOneId: UUID, playerTwoId: UUID, playerRepo: PlayerRepo, deckRepo: DeckRepo) {
    val deck = Deck()
    deck.shuffleCards()
    val handCards = deck.dealCards(playerOneId, playerTwoId)
    val playerOne = Player(
        id = playerOneId,
        handCards = handCards[playerOneId]!!.toMutableList()
    )
    val playerTwo = Player(
        id = playerOneId,
        handCards = handCards[playerOneId]!!.toMutableList()
    )
    deck.initializeStacks()
    // Save players and deck
    playerRepo.save(playerOne)
    playerRepo.save(playerTwo)
    deckRepo.save(deck)
}
