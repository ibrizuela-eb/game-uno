//package repos
//
//abstract class InMemoryRepo<T> {
//    val entities: MutableList<T> = mutableListOf()
//
//    fun findByCriteria(criteria: (t: T) -> Boolean): List<T> {
//        return entities.filter(criteria)
//    }
//
//    fun add()
//}
