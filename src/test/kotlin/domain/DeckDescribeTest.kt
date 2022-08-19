package domain

import factories.DeckFactory
import io.kotest.core.spec.style.DescribeSpec

class DeckDescribeTest : DescribeSpec({
    describe("can play card") {
        it("can place a color card over other color card with same value") {
            val deckMocked = DeckFactory {}
            val twoRedCard = GAME_CARDS["2R"]
            deckMocked.canPlayCard(twoRedCard!!)
        }
    }
})
