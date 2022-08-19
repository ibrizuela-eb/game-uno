package repos

import domain.Game
import org.springframework.stereotype.Repository
import java.util.UUID

interface GameRepo {
    fun save(player: Game)
    fun findById(id: UUID): Game?
}

@Repository
class GameRepoInMemory : GameRepo {
    private val games = mutableListOf<Game>()

    override fun save(game: Game) {
        games.add(game)
    }

    override fun findById(id: UUID): Game? {
        return games.firstOrNull { it.id == id }
    }
}

// class GameRepo private constructor() {
//    private val games = mutableListOf<Game>()
//
//    fun save(game: Game) {
//        games.add(game)
//    }
//
//    fun findById(id: UUID): Game? {
//        return games.firstOrNull { it.id == id }
//    }
//
//    companion object {
//        var gameRepo: GameRepo? = null
//
//        fun getInstance(): GameRepo {
//            if (gameRepo == null) {
//                gameRepo = GameRepo()
//            }
//            return gameRepo!!
//        }
//    }
// }
