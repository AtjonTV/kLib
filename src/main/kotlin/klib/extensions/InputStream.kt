package klib.extensions

import klib.annotations.Experimental
import klib.types.zip.ZipFile
import java.io.File
import java.io.InputStream

/**
 * Write InputStream into File
 *
 * @param file The file to write into
 *
 * @since 1.2.0
 * @author Thomas Obernosterer
 */
fun InputStream.toFile(file: File) {
    file.outputStream().use { this.copyTo(it) }
}

/**
 * Write InputStream into a File inside a ZipFile
 *
 * @param file The file to write into
 * @param zipFile The zipFile the file gets added to
 *
 * @since 1.2.0
 * @author Thomas Obernosterer
 */
@UseExperimental(Experimental::class)
fun InputStream.toFileInZipFile(file: File, zipFile: ZipFile) {
    toFile(file)
    zipFile.addFile(file)
}