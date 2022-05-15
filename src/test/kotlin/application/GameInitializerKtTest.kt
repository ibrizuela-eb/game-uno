package application

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import repos.DeckRepo
import repos.PlayerRepo
import java.util.UUID

class GameInitializerKtTest : StringSpec({
    "players should have handcards when initializeGame" {
        val playerRepo = PlayerRepo()
        val deckRepo = DeckRepo()
        val playerOneId = UUID.randomUUID()
        val playerTwoId = UUID.randomUUID()
        val deckId = UUID.randomUUID()
        initializeGame(
            playerOneId = playerOneId,
            playerTwoId = playerTwoId,
            deckId = deckId,
            playerRepo = playerRepo,
            deckRepo = deckRepo
        )
        val playerOneHandCards = playerRepo.findById(playerOneId)!!.handCards
        val playerTwoHandCards = playerRepo.findById(playerTwoId)!!.handCards
        playerOneHandCards.size shouldNotBe null
        playerTwoHandCards.size shouldNotBe null
    }

    "initializeGame should have live card when it is called" {
        val playerRepo = PlayerRepo()
        val deckRepo = DeckRepo()
        val playerOneId = UUID.randomUUID()
        val playerTwoId = UUID.randomUUID()
        val deckId = UUID.randomUUID()
        initializeGame(
            playerOneId = playerOneId,
            playerTwoId = playerTwoId,
            deckId = deckId,
            playerRepo = playerRepo,
            deckRepo = deckRepo
        )
        val liveCard = deckRepo.findById(deckId)!!.getLiveCard()
        liveCard shouldNotBe null
    }
})
