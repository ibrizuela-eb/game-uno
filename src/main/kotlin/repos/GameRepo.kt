package repos

import domain.Game
import java.util.UUID

class GameRepo private constructor() {
    private val games = mutableListOf<Game>()

    fun save(game: Game) {
        games.add(game)
    }

    fun findById(id: UUID): Game? {
        return games.firstOrNull { it.id == id }
    }

    companion object {
        var gameRepo: GameRepo? = null

        fun getInstance(): GameRepo {
            if (gameRepo == null) {
                gameRepo = GameRepo()
            }
            return gameRepo!!
        }
    }
}
