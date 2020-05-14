package cz.my2n.lib.utils.kscript

private const val ANSI_GREEN = "\u001B[32m"
private const val ANSI_RED = "\u001B[31m"
private const val ANSI_RESET = "\u001B[0m"

/**
 * Prints info message
 * @param message
 */
fun info(message: String) = println("$ANSI_RESET$message$ANSI_RESET")

/**
 * Prints errors message
 * @param message
 */
fun error(message: String) = println("$ANSI_RED$message$ANSI_RESET")

/**
 * Prints success message
 * @param message
 */
fun success(message: String) = println("$ANSI_GREEN$message$ANSI_RESET")
