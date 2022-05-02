package domain

import java.util.UUID

class Player(
    val id: UUID = UUID.randomUUID(),
    handCards: MutableList<String> = mutableListOf()
) {
    val handCards: MutableList<String> = handCards
        get() = field.toMutableList()

    companion object {
        fun create(): Player {
            return Player()
        }
    }
}
