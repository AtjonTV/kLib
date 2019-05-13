package klib.dummy

import klib.interfaces.Json
import java.lang.reflect.Type

/**
 * Dummy JsonHandler
 *
 * @since 0.1.5
 * @author Nils Rider
 */
class JsonHandler : Json {
    override fun fromObject(data: Any): String {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun toObject(data: String, type: Type): Any? {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}