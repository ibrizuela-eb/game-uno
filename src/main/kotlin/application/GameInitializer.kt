package application

import domain.Deck
import domain.Player
import repos.DeckRepo
import repos.PlayerRepo
import java.util.UUID

data class GameElements(
    val playerOneId: UUID,
    val playerTwoId: UUID,
    val deckId: UUID,
)

fun initializeGame(
    gameElements: GameElements,
    playerRepo: PlayerRepo,
    deckRepo: DeckRepo
) {
    val deck = Deck(id = gameElements.deckId)
    deck.shuffleCards()
    val handCards = deck.dealCards(
        playerOneId = gameElements.playerOneId,
        playerTwoId = gameElements.playerTwoId
    )
    val playerOne = Player(
        id = gameElements.playerOneId,
        handCards = handCards[gameElements.playerOneId]!!.toMutableList()
    )
    val playerTwo = Player(
        id = gameElements.playerTwoId,
        handCards = handCards[gameElements.playerTwoId]!!.toMutableList()
    )
    deck.initializeStacks()
    // Save players and deck
    playerRepo.save(playerOne)
    playerRepo.save(playerTwo)
    deckRepo.save(deck)
}
