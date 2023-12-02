fun main() = println(
    generateSequence(::readLine)
        .mapNotNull { l ->
            l.split(':')
                .let { (k, v) ->
                    k.substring(5).toInt() to v.split(';').map { d ->
                        d.split(',').associate { g ->
                            g.filterNot(Char::isWhitespace)
                                .partition { !it.isDigit() }
                                .run { first to second.toInt() }
                        }
                    }
                }
                .takeIf { (_, v) -> v.all { (it["red"] ?: 0) < 13 && (it["green"] ?: 0) < 14 && (it["blue"] ?: 0) < 15 } }
        }
        .sumOf { it.first }
)