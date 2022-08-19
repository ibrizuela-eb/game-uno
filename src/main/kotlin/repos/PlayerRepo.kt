package repos

import domain.Player
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * Singleton: Es un objeto global para toda la aplicacion
 * https://refactoring.guru/design-patterns/singleton
 * 1. Cuando quieren un objeto por primera vez el objeto se inicializa
 * 2. Todas las llamadas subsecuentes devuelven el mismo objeto
 *
 * Como solo va a existir una instancia sola, se puede defirnir directamente como
 * object en vez de class. Esto evita el uso del metodo getInstance()
 */

interface PlayerRepo {
    fun save(player: Player)
    fun findById(id: UUID): Player?
}

/**
 * La anotacion de @Repository indica que esta es la dependencia a utilizarse en
 * el IoC
 * IoC.addInstance(PlayerRepo, PlayerRepoInMemory)
 */
@Repository
object PlayerRepoInMemory : PlayerRepo {
    private val players = mutableListOf<Player>()

    override fun save(player: Player) {
        players.add(player)
    }

    override fun findById(id: UUID): Player? {
        return players.firstOrNull { it.id == id }
    }
}

// class PlayerMySQLRepo : PlayerRepo {
//    val sessionFactory = Session.getFactory()
//
//    override fun save(player: Player) {
//        val session = sessionFactory.getSession()
//        session.insert(player)
//    }
//
//    override fun findById(id: UUID): Player? {
//        return PlayerRepoInMemory.players.firstOrNull { it.id == id }
//    }
// }

class PlayerRepoV2 private constructor() {
    private val players = mutableListOf<Player>()

    fun save(player: Player) {
        players.add(player)
    }

    fun findById(id: UUID): Player? {
        return players.firstOrNull { it.id == id }
    }

    /**
     * Companion objects son metodos de clase
     * Singlenton pattern
     * Este metodo pregunta si la clase ya fue instanciada y de ser asi devuelve
     * dicha instancia, caso contrario crea una nueva
     */
    companion object {
        var playerRepo: PlayerRepoV2? = null

        fun getInstance(): PlayerRepoV2 {
            if (playerRepo == null) {
                playerRepo = PlayerRepoV2()
            }
            return playerRepo!!
        }
    }
}
