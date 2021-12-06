import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

object ResourceFileReader {
    fun readFile(name: String) : List<String> = this::class.java.classLoader.getResource(name)
        .readText()
        .split(System.lineSeparator())
        .filter { it.isNotBlank() }
        .map { it.strip() }
}

/**
 * Reads lines from the given input txt file.
 */
fun resource(name: String) = ResourceFileReader.readFile(name)

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
