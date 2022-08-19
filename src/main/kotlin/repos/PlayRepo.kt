package repos

import domain.Play
import org.springframework.stereotype.Repository
import java.util.UUID

interface PlayRepo {
    fun save(player: Play)
    fun findActivePlay(gameId: UUID): Play?
}

@Repository
class PlayRepoInMemory : PlayRepo {
    private val plays = mutableListOf<Play>()

    override fun save(play: Play) {
        plays.add(play)
    }

    override fun findActivePlay(gameId: UUID): Play? {
        return plays.firstOrNull { it.gameId == gameId && it.isActive }
    }
}

// class PlayRepo {
//    private val plays = mutableListOf<Play>()
//
//    fun save(play: Play) {
//        plays.add(play)
//    }
//
//    fun findActivePlay(gameId: UUID): Play? {
//        return plays.firstOrNull { it.gameId == gameId && it.isActive }
//    }
//
//    companion object {
//        var playRepo: PlayRepo? = null
//
//        fun getInstance(): PlayRepo {
//            if (playRepo == null) {
//                playRepo = PlayRepo()
//            }
//            return playRepo!!
//        }
//    }
// }
