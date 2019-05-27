package klib.types.library

import java.io.FileNotFoundException
import java.lang.reflect.Method
import java.lang.reflect.Modifier

/**
 * Custom Class to handle Dynamically loaded Classes
 *
 * @param sourceClass A Class<*> that serves as the Host for further use
 *
 * @since 1.3.0 (Experimental)
 * @author Thomas Obernosterer
 */
@klib.annotations.Experimental
class LClass(
    private val sourceClass: Class<*>
) {
    private val methods: MutableList<Method> = ArrayList()

    init {
        var clazz: Class<*>? = sourceClass

        while (clazz != null) {
            for (method in clazz.declaredMethods) {
                val mods = method.modifiers
                if (Modifier.isPrivate(mods) || Modifier.isPublic(mods)) {
                    methods.add(method)
                }
            }
            clazz = clazz.superclass
        }
    }

    /**
     * Get a function from the Host class and execute it
     *
     * @param method The function to load
     * @return Unknown (Defined by Dynamic Library)
     * @throws NoSuchMethodException
     *
     * @since 1.4.0 (Experimental)
     * @author Thomas Obernosterer
     */
    @Throws(NoSuchMethodException::class)
    operator fun invoke(method: String): Any? {
        return getMethod(method).invoke()
    }

    /**
     * Get a function from the Host class and execute it
     *
     * @param method The function to load
     * @param param A single arguments for the function
     * @return Unknown (Defined by Dynamic Library)
     * @throws NoSuchMethodException
     *
     * @since 1.4.0 (Experimental)
     * @author Thomas Obernosterer
     */
    @Throws(NoSuchMethodException::class)
    operator fun invoke(method: String, param: Any?): Any? {
        return getMethod(method).invoke(param)
    }

    /**
     * Get a function from the Host class and execute it
     *
     * @param method The function to load
     * @param params A list of arguments for the function
     * @return Unknown (Defined by Dynamic Library)
     * @throws NoSuchMethodException
     *
     * @since 1.4.0 (Experimental)
     * @author Thomas Obernosterer
     */
    @Throws(NoSuchMethodException::class)
    operator fun invoke(method: String, vararg params: Any?): Any? {
        return getMethod(method).invoke(params)
    }

    /**
     * Get a function from the Host class
     *
     * @param functionName The function to load
     * @return A LFunction
     * @throws FileNotFoundException
     * @throws NoSuchMethodException
     *
     * @see LFunction
     *
     * @since 1.3.0 (Experimental)
     * @author Thomas Obernosterer
     */
    @Throws(FileNotFoundException::class, NoSuchMethodException::class)
    fun getMethod(functionName: String): LFunction {
        val method = methods.find { it.name == functionName } ?: throw NoSuchMethodException()
        val classObject = sourceClass.newInstance()
        return LFunction(classObject, method)
    }

    /**
     * Get all defined Methods from Host class
     *
     * @return List of LFunction
     *
     * @see LFunction
     *
     * @since 1.4.0 (Experimental)
     * @author Thomas Obernosterer
     */
    fun getAllMethods(): List<LFunction> {
        val functions: MutableList<LFunction> = ArrayList()
        val classObject = sourceClass.newInstance()
        methods.forEach {
            functions.add(LFunction(classObject, it))
        }
        return functions
    }
}