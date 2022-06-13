package repos

import domain.Play
import java.util.UUID

class PlayRepo {
    private val plays = mutableListOf<Play>()

    fun save(play: Play) {
        plays.add(play)
    }

    fun findActivePlay(gameId: UUID): Play? {
        return plays.firstOrNull { it.gameId == gameId && it.isActive}
    }

    companion object {
        var playRepo: PlayRepo? = null

        fun getInstance(): PlayRepo {
            if (playRepo == null) {
                playRepo = PlayRepo()
            }
            return playRepo!!
        }
    }
}
