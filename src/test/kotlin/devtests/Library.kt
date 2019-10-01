package devtests

import klib.functions.loadClass

/**
 * Source of hello.kt

package hello

class Hello {
    fun hi() {
        println("Hello from Hello class")
    }
}

 * Compile using:
 * kotlinc -d hello.jar hello.kt
 */

fun main() {
    val hello = loadClass {
        file = "/tmp/hello.jar"
        className = "hello.Hello"
    }

    hello("hi")
}