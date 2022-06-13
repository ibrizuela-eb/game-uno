package domain

interface CardAction {
    fun apply(deck: Deck, targetPlayer: Player)
}

class AddTwoCards : CardAction {
    override fun apply(deck: Deck, targetPlayer: Player) {
        val cards = deck.getCards(n = 2)
        targetPlayer.addCards(cards)
    }
}

class AddFourCards : CardAction {
    override fun apply(deck: Deck, targetPlayer: Player) {
        val cards = deck.getCards(n = 4)
        targetPlayer.addCards(cards)
    }
}

class NoEffect : CardAction {
    override fun apply(deck: Deck, targetPlayer: Player) {
    }
}

/**
 * Una selaed class solo permite tener hijos dentro del mismo archivo
 */
abstract sealed class Card(val action: CardAction) {
    abstract fun validate(
        liveCard: String,
        moveCards: List<String>
    ): Boolean

    fun apply(
        deck: Deck,
        targetPlayer: Player
    ) {
        // TEMPLATE METHOD pattern
//        if (!validate(liveCard, moveCards))
//            throw Error("")
        action.apply(deck, targetPlayer)
    }
}

class ColorCard(val value: String, val color: String, action: CardAction) : Card(action) {
    override fun validate(
        liveCard: String,
        moveCards: List<String>
    ): Boolean = true
}

class WildCard(val value: String, action: CardAction, val color: String? = null) : Card(action) {
    override fun validate(
        liveCard: String,
        moveCards: List<String>
    ): Boolean = true
}

val noEffect = NoEffect()
val GAME_CARDS = mapOf<String, Card>(
    "+4" to WildCard("+4", AddFourCards()),
    "#" to WildCard("#", noEffect),
    "+2R" to ColorCard("+2", "R", AddTwoCards()),
    "0R" to ColorCard("0", "R", noEffect),
    "1R" to ColorCard("1", "R", noEffect),
    "2R" to ColorCard("2", "R", noEffect),
    "3R" to ColorCard("3", "R", noEffect),
    "4R" to ColorCard("4", "R", noEffect),
    "5R" to ColorCard("5", "R", noEffect),
    "6R" to ColorCard("6", "R", noEffect),
    "7R" to ColorCard("7", "R", noEffect),
    "8R" to ColorCard("8", "R", noEffect),
    "9R" to ColorCard("9", "R", noEffect),
    "(X)R" to ColorCard("(X)", "R", noEffect),
    "<>R" to ColorCard("<>", "R", noEffect),
    "+2B" to ColorCard("+2", "B", AddTwoCards()),
    "0B" to ColorCard("0", "B", noEffect),
    "1B" to ColorCard("1", "B", noEffect),
    "2B" to ColorCard("2", "B", noEffect),
    "3B" to ColorCard("3", "B", noEffect),
    "4B" to ColorCard("4", "B", noEffect),
    "5B" to ColorCard("5", "B", noEffect),
    "6B" to ColorCard("6", "B", noEffect),
    "7B" to ColorCard("7", "B", noEffect),
    "8B" to ColorCard("8", "B", noEffect),
    "9B" to ColorCard("9", "B", noEffect),
    "(X)B" to ColorCard("(X)", "B", noEffect),
    "<>B" to ColorCard("<>", "B", noEffect),
    "+2G" to ColorCard("+2", "G", AddTwoCards()),
    "0G" to ColorCard("0", "G", noEffect),
    "1G" to ColorCard("1", "G", noEffect),
    "2G" to ColorCard("2", "G", noEffect),
    "3G" to ColorCard("3", "G", noEffect),
    "4G" to ColorCard("4", "G", noEffect),
    "5G" to ColorCard("5", "G", noEffect),
    "6G" to ColorCard("6", "G", noEffect),
    "7G" to ColorCard("7", "G", noEffect),
    "8G" to ColorCard("8", "G", noEffect),
    "9G" to ColorCard("9", "G", noEffect),
    "(X)G" to ColorCard("(X)", "G", noEffect),
    "<>G" to ColorCard("<>", "G", noEffect),
    "+2Y" to ColorCard("+2", "Y", AddTwoCards()),
    "0Y" to ColorCard("0", "Y", noEffect),
    "1Y" to ColorCard("1", "Y", noEffect),
    "2Y" to ColorCard("2", "Y", noEffect),
    "3Y" to ColorCard("3", "Y", noEffect),
    "4Y" to ColorCard("4", "Y", noEffect),
    "5Y" to ColorCard("5", "Y", noEffect),
    "6Y" to ColorCard("6", "Y", noEffect),
    "7Y" to ColorCard("7", "Y", noEffect),
    "8Y" to ColorCard("8", "Y", noEffect),
    "9Y" to ColorCard("9", "Y", noEffect),
    "(X)Y" to ColorCard("(X)", "Y", noEffect),
    "<>Y" to ColorCard("<>", "Y", noEffect),
)
