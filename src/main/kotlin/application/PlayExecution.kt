package application

import domain.Deck
import domain.Play
import domain.Player
import org.springframework.stereotype.Service
import repos.DeckRepo
import repos.GameRepo
import repos.PlayRepo
import repos.PlayerRepo
import java.util.UUID

/**
 * Una sealed class solo permite tener hijos dentro del mismo archivo
 */
// sealed class PlayerAction {
//    companion object {
//        fun fromString(action: String): PlayerAction {
//            return when(action) {
//                "PASS_TURN" -> PassTurn
//                "TAKE_CARD" -> PassTurn
//                "PLAY_CARD" -> PlayCard
//            }
//        }
//    }
// }
// object PassTurn: PlayerAction()
// object TakeCard: PlayerAction()
// data class PlayCard(val card: String): PlayerAction()
enum class PlayerAction {
    PASS_TURN,
    TAKE_CARD,
    PLAY_CARD
}

@Service
class PlayExcecutor(
    val playerRepo: PlayerRepo,
    val deckRepo: DeckRepo,
    val gameRepo: GameRepo,
    val playRepo: PlayRepo,
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
    fun run(
        playerId: UUID,
        gameId: UUID,
        action: String,
        cardRepresentation: String? = null
    ) {
        val currentPlayer = playerRepo.findById(
            id = playerId,
        ) ?: throw IllegalStateException("Player does not exist")
        val currentGame = gameRepo.findById(
            id = gameId,
        ) ?: throw IllegalStateException("Game does not exist")
        val currentDeck = deckRepo.findById(
            id = currentGame.deckId,
        ) ?: throw IllegalStateException("Deck does not exist")
        val currentPlay = playRepo.findActivePlay(
            gameId = currentGame.id,
        ) ?: Play(gameId = currentGame.id)

        when (PlayerAction.valueOf(action)) {
            PlayerAction.PASS_TURN -> currentGame.passTurn()
            PlayerAction.TAKE_CARD -> currentPlayer.addCards(listOf(currentDeck.getCard()))
            PlayerAction.PLAY_CARD -> if (cardRepresentation == null) throw Error("Card could not be null") else TODO()
        }

        playerRepo.save(currentPlayer)
        deckRepo.save(currentDeck)
        gameRepo.save(currentGame)
        playRepo.save(currentPlay)
    }
}

fun playCard(player: Player, deck: Deck, play: Play, cardRepresentation: String) {
    // si el jugador puede colocar la carta, debo quitarsela de su mano de cartas
    // y colocarla como la carta en juego en el Deck
    // si no puede jugar esa carta se lo penaliza ejecutandole todas las acciones
    // en la cola de jugada (en caso de que haya), y pasa el turno
    val card = player.getCard(cardRepresentation)
    if (!deck.canPlayCard(card)) {
        throw Error("You cannot play that card!")
    }
    play.addCardToQueue(card)
    deck.placeCard(cardRepresentation)
    player.removeCard(cardRepresentation)
    // En todos lados se deberia consumir card en vez del String
}
