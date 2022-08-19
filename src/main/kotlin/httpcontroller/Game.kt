package httpcontroller

import application.GameElements
import application.GameInitializer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import repos.GameRepo
import java.util.UUID

@RestController // Declaracion del bean de Rest controller
@RequestMapping("/api") // Prefijo de todos los endpoints. e.g: /api/games
class Game(val gameInitializer: GameInitializer, val gameRepo: GameRepo) {
    /**
     * El autowire es lo mismo que definir las variables en el constructor
     */
//    @Autowired
//    lateinit var gameInitializer: GameInitializer

    data class PlayerIds(val playerOne: String, val playerTwo: String)

    // uno startGame --playerOne=12asdasd --playerTwo=12asdasd
    @PostMapping("/games")
    fun startGame(@RequestBody playerIds: PlayerIds): ResponseEntity<*> {
        /**
         * Input >>
         * {
         "playerOne": "18b2218e-2afc-457a-8bc5-0559e8927e30",
         "playerTwo": "ffc73ef1-bba3-4e56-9c79-43837dffb791"
         }
         * Output >>
         * {
         "gameId": "fafaf813-4727-4678-9737-e07c9b869217"
         }
         */
        val deckId = UUID.randomUUID()
        val gameId = UUID.randomUUID()

//        val gameInitializer = GameInitializer(PlayerRepo.getInstance(), DeckRepo.getInstance())
        gameInitializer.run(
            GameElements(
                playerOneId = UUID.fromString(playerIds.playerOne),
                playerTwoId = UUID.fromString(playerIds.playerTwo),
                deckId,
            ),
            gameId,
        )
        return ResponseEntity.ok(
            hashMapOf(
                "gameId" to gameId
            )
        )
    }

    @GetMapping("/games/{gameId}")
    fun getGame(@PathVariable gameId: UUID): ResponseEntity<*> {
        /**
         * Input >>
         * {
         "gameId": "18b2218e-2afc-457a-8bc5-0559e8927e30",
         }
         * Output >>
         * {
         "gameId": "fafaf813-4727-4678-9737-e07c9b869217"
         }
         */
        val game = gameRepo.findById(gameId)
        return if (game != null) {
            ResponseEntity.ok(
                hashMapOf(
                    "game" to game!!.toResponse()
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found")
        }
    }
}
