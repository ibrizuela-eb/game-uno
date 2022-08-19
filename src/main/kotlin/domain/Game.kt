package domain

import java.util.UUID

data class GameResponse(
    val id: String,
    val deckId: String,
    val players: List<PlayerResponse>,
    val currentPlayer: String,
)

class Game(
    val deckId: UUID,
    val id: UUID = UUID.randomUUID(),
    val players: List<Player> = listOf(),
    var currentPlayerIndex: Int = 0
) {
    fun passTurn() {
        currentPlayerIndex += 1
        if (currentPlayerIndex >= players.size) {
            currentPlayerIndex = 0
        }
    }

    fun toResponse(): GameResponse {
        return GameResponse(
            id = id.toString(),
            deckId = deckId.toString(),
            players = players.map { it.toResponse() },
            currentPlayer = players.elementAt(currentPlayerIndex).toString()
        )
    }
}
