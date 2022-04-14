package domain

import java.util.UUID

class Player(
    val id: UUID = UUID.randomUUID(),
    private val handCards: MutableList<String> = mutableListOf()
) {
    companion object {
        fun create(): Player {
            return Player()
        }
    }
}
