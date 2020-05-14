package cz.my2n.lib.utils.kscript

import com.github.andrewoma.kwery.core.DefaultSession
import com.github.andrewoma.kwery.core.Row
import com.github.andrewoma.kwery.core.dialect.Dialect
import java.sql.Connection
import java.sql.DriverManager

/**
 * Creates database connection for given connection string
 * @param conString connection string
 */
fun createConnection(conString: String): Connection? {
    return DriverManager.getConnection(conString)
}

/**
 * Generic inline function for realizing a db select query.
 * @param query query is basic sql query e.g. "select id from device where sipNumber = :sipNumber"
 * @param params query parameters Map<"paramName", "paramValue">, default empty map
 * @param connection db connection instance
 * @param dialect sql dialect
 * @param mapping a function to map the Row object to desired type e.g. Device, Site etc.
 */
inline fun <reified T> select(query: String, params: Map<String, String> = mapOf(), connection: Connection, dialect: Dialect, crossinline mapping: (Row) -> T): Set<T> {
    val session = DefaultSession(connection, dialect)
    return session.select(query, params) { row ->
        mapping(row)
    }.toSet()
}
