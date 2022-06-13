package repos

import domain.Player
import java.util.UUID

/**
 * Singleton: Es un objeto global para toda la aplicacion
 * https://refactoring.guru/design-patterns/singleton
 * 1. Cuando quieren un objeto por primera vez el objeto se inicializa
 * 2. Todas las llamadas subsecuentes devuelven el mismo objeto
 */
class PlayerRepo private constructor() {
    private val players = mutableListOf<Player>()

    fun save(player: Player) {
        players.add(player)
    }

    fun findById(id: UUID): Player? {
        return players.firstOrNull { it.id == id }
    }

    companion object {
        var playerRepo: PlayerRepo? = null

        fun getInstance(): PlayerRepo {
            if (playerRepo == null) {
                playerRepo = PlayerRepo()
            }
            return playerRepo!!
        }
    }
}
