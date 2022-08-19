package server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@SpringBootApplication
@ComponentScans(
    // Para forzar la busqueda de Beans en paquetes especificos
    // Por defecto busca en el paquete actual o subsiguientes: e.g server
    value = [
        ComponentScan("application"),
        ComponentScan("repos"),
        ComponentScan("httpcontroller"),
    ]
)
class HttpServer

// IoC contenedor de inyeccion de dependencias: Registra las instancias concretas de las interfaces o tambien registra las instancias
// concretas definidas en la aplicacion

// Se inicializa el IoC con los Beans cuando arranca Spring
fun main(args: Array<String>) {
    // IoC
    /*
       interface PlayerRepo -> class PlayerMySQLRepo // val repo = PlayerMySQLRepo()
       interface DeckRepo -> class DeckMySQLRepo
     */

    /*
        Anotaciones tipicas para registrar un Bean:
        @Component: Registra un bean de forma generica
        @Repository: Registra un bean de estereotipo repository
        @Service: Registra un bean de estereotipo service
     */

    /*
        1.Registrar beans
        2.Tengo que ir a los paquetes application y repos
        3. Registrar los repos - El IoC va a tener
            interface PlayerRepo -> class PlayerMySQLRepo // val repo = PlayerMySQLRepo()
            class DeckRepo -> val repo = DeckRepo()
        4. Registrar los services -
            val gameInitializer = GameInitializer(repo, repo2)
        5. Registra el RestController -
            val restController = GameInitializer(repo, repo2)
     */

    val app = runApplication<HttpServer>(*args)
}
