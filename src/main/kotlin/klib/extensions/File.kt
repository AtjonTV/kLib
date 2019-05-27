package klib.extensions

import klib.annotations.Experimental
import klib.objects.hash.Sha1
import klib.objects.hash.Sha256
import klib.objects.library.Library
import klib.types.hash.HashResult
import klib.types.library.LClass
import klib.types.library.LFunction
import klib.types.zip.ZipFile
import java.io.File
import java.io.FileNotFoundException

/**
 * Add a file to a ZipFile
 *
 * @param zipFile The file to add to
 *
 * @since 1.2.0
 * @author Thomas Obernosterer
 */
@UseExperimental(Experimental::class)
fun File.addToZipFile(zipFile: ZipFile) = zipFile.addFile(this)

/**
 * Load the file as library and load a class from it
 *
 * @param className The class to load
 * @return a LClass with the loaded class
 * @throws FileNotFoundException
 * @throws ClassNotFoundException
 *
 * @since 1.3.0 (Experimental)
 * @author Thomas Obernosterer
 */
@UseExperimental(Experimental::class)
@Throws(FileNotFoundException::class, ClassNotFoundException::class)
infix fun File.loadAsLibraryWithClass(className: String): LClass {
    return Library.loadClassFromJar(this, className)
}

/**
 * Load the file as library and load a method from it
 *
 * @param className The class to load from
 * @param functionName The method to load
 * @return a LFunction with the loaded method
 * @throws FileNotFoundException
 * @throws ClassNotFoundException
 * @throws NoSuchMethodException
 *
 * @since 1.3.0 (Experimental)
 * @author Thomas Obernosterer
 */
@UseExperimental(Experimental::class)
@Throws(FileNotFoundException::class, ClassNotFoundException::class, NoSuchMethodException::class)
fun File.loadAsLibraryWithFunction(className: String, functionName: String): LFunction {
    return Library.loadFunctionFromJar(this, className, functionName)
}

/**
 * Hash the content of the File with SHA256
 *
 * @since 2.0.0
 * @author Thomas Obernosterer
 */
fun File.hashSha256(): HashResult {
    return Sha256.hash(this.readBytes())
}

/**
 * Hash the content of the File with SHA1
 *
 * @since 2.0.0
 * @author Thomas Obernosterer
 */
fun File.hashSha1(): HashResult {
    return Sha1.hash(this.readBytes())
}

/**
 * Hash the content of the File with SHA256 and return 4 Bytes
 *
 * @since 2.0.0
 * @author Thomas Obernosterer
 */
fun File.Sha256Checksum(): String {
    return Sha256.hash(this.readBytes()).checksum
}

/**
 * Hash the content of the File with SHA1 and return 4 Bytes
 *
 * @since 2.0.0
 * @author Thomas Obernosterer
 */
fun File.Sha1Checksum(): String {
    return Sha1.hash(this.readBytes()).checksum
}