fun main() = println(generateSequence(::readLine).sumOf { it.filter(Char::isDigit).run { "${first()}${last()}".toInt() } })