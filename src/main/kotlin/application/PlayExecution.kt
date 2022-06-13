package application

import domain.Card
import repos.DeckRepo
import repos.PlayRepo
import repos.PlayerRepo
import java.util.UUID

enum class PlayerAction {
    PASS_TURN,
    TAKE_CARD,
    PLAY_CARD
}

fun executePlay(
    playerId: UUID,
    deckId: UUID,
    action: String,
    playerRepo: PlayerRepo = PlayerRepo.getInstance(),
    deckRepo: DeckRepo = DeckRepo.getInstance()
) {
    /**
     * Pasar los repos por argumento: INVERSION DE DEPENDENCIAS
     * Ventajas
     * 1. Facilidad de mockear infraestructura en testing
     * 2. Codigo menos acoplado a infrastructura
     * Desventajas
     * 1. Obligas al cliente que conozca la infrastructura que tenes que utilizas
     * 2. No respeta encapsulamiento
     */
    /**
     * Inyeccion de dependencias
     * 1. Cuando se startea la aplicacion --> IOC contenedor de inyeccion de dependencias
     * 2. IOC registra los beans configurados con anotaciones en Spring
     * 3. Le podes pedir al IOC que te de una instancia segun lo que se configuro
     */
    val currentPlayer = playerRepo.findById(
        id = playerId,
    ) ?: throw IllegalStateException("Player does not exist")
    val currentDeck = deckRepo.findById(
        id = deckId,
    ) ?: throw IllegalStateException("Deck does not exist")

    val playerAction = PlayerAction.valueOf(action)
    val bla = when (playerAction) {
        PlayerAction.PASS_TURN -> println("")
        PlayerAction.TAKE_CARD -> currentPlayer.addCards(listOf(currentDeck.getCard()))
        PlayerAction.PLAY_CARD -> println("")
    }

    playerRepo.save(currentPlayer)
}

fun playCard(card: Card) {
    val playRepo = PlayRepo.getInstance()
    val play = playRepo.findActivePlay(gameId = gameId)
    //
}
