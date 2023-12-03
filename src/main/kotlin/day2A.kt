fun main() = println(
    generateSequence(::readLine)
        .mapNotNull { l ->
            l.split(':')
                .let { (k, v) ->
                    k.substring(5).toInt() to v.split(';').map { d ->
                        d.split(',').associate { g ->
                            g.trim().split(' ')
                                .let { (a, b) -> b to a.toInt() }
                        }
                    }
                }
                .takeIf { (_, v) -> v.all { (it["red"] ?: 0) <= 12 && (it["green"] ?: 0) <= 13 && (it["blue"] ?: 0) <= 14 } }
        }
        .sumOf { it.first }
)