package repos

import domain.Player
import java.util.UUID

class PlayerRepo {
    private val players = mutableListOf<Player>()

    fun save(player: Player) {
        players.add(player)
    }

    fun findById(id: UUID): Player? {
        return players.firstOrNull { it.id == id }
    }
}
