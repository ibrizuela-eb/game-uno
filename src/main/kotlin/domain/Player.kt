package domain

import java.util.UUID

class Player (
    val id: UUID = UUID.randomUUID(),
    private val handCards: MutableList<String> = mutableListOf()
){
    companion object{
        fun create(): Player{
            return Player(handCards = mutableListOf("2R","5B", "+4", "1Y", "6Y"))
        }
    }
}