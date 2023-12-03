// had to rethink my strategy for this one
data class Gear(val c: Char, val x: Int, val y: Int)

fun main() = println(
    generateSequence(::readLine)
        .toList()
        .let { ls ->
            ls
                .flatMapIndexed { x, l ->
                    l.mapIndexedNotNull { y, c ->
                        c.takeIf { it == '*' }
                            ?.let { Gear(it, x, y) }
                    }
                }
                .map { g ->
                    ls.subList(g.x - 1, g.x + 2)
                        .flatMap { s ->
                            g.y.takeIf { it < s.length && s[it].isDigit() }
                                // code duplication ugh
                                ?.let {
                                    listOf(
                                        (s.substring(0, it).takeLastWhile(Char::isDigit) + s.substring(it)
                                            .takeWhile(Char::isDigit)).toInt()
                                    )
                                }
                                ?: listOf(g.y - 1, g.y + 1).mapNotNull { y ->
                                    y.takeIf { it < s.length && s[it].isDigit() }
                                        ?.let {
                                            (s.substring(0, it).takeLastWhile(Char::isDigit) + s.substring(it)
                                                .takeWhile(Char::isDigit)).toInt()
                                        }
                                }
                        }
                }
        }
        .filter { it.size == 2 }
        .sumOf { (a, b) -> a * b }
)