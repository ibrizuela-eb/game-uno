package application

import domain.Deck
import domain.Game
import domain.Player
import org.springframework.stereotype.Service
import repos.DeckRepo
import repos.GameRepo
import repos.PlayerRepo
import java.util.UUID

data class GameElements(
    val playerOneId: UUID,
    val playerTwoId: UUID,
    val deckId: UUID,
)

@Service
class GameInitializer(
    val playerRepo: PlayerRepo,
    val deckRepo: DeckRepo,
    val gameRepo: GameRepo,
) {
    fun run(gameElements: GameElements, gameId: UUID) {
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
        // Save players, deck and game
        val game = Game(
            id = gameId,
            deckId = gameElements.deckId,
            players = mutableListOf(playerOne, playerTwo)
        )
        playerRepo.save(playerOne)
        playerRepo.save(playerTwo)
        deckRepo.save(deck)
        gameRepo.save(game)
    }
}
