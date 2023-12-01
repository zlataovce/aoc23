val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            StringBuilder(l).apply {
                indices.forEach { i ->
                    digits.indexOfFirst { startsWith(it, i) }
                        .takeIf { it != -1 }
                        ?.let { deleteRange(i, i + digits[it].length).insert(i, it.inc()) }
                }
            }
        }
        .sumOf { it.filter(Char::isDigit).run { "${first()}${last()}".toInt() } }
)