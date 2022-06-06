package factories

import domain.Player
import java.util.UUID

class PlayerFactory {
    var id: UUID = UUID.randomUUID()!!
    var handCards = mutableListOf<String>()

    companion object {
        operator fun invoke(init: PlayerFactory.() -> Unit): Player {
            val factory = PlayerFactory()
            factory.init()
            return Player(factory.id, factory.handCards)
        }

//        operator fun invoke(init: (PlayerFactory) -> Unit): Player {
//            val factory = PlayerFactory()
//            init(factory)
//            return Player(factory.id, factory.handCards)
//        }
    }
}
