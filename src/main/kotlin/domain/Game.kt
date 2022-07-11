package domain

import java.util.UUID

class Game(
    val deckId: UUID,
    val id: UUID = UUID.randomUUID(),
    val players: List<Player> = listOf(),
    var currentPlayerIndex: Int = 0
) {
    fun passTurn() {
        currentPlayerIndex += 1
        if (currentPlayerIndex >= players.size){
            currentPlayerIndex = 0
        }
    }
}
