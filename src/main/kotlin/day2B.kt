fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            l.split(':')
                .let { (_, v) ->
                    v.split(';')
                        .flatMap { d ->
                            d.split(',').map { g ->
                                g.filterNot(Char::isWhitespace)
                                    .partition { !it.isDigit() }
                                    .run { first to second.toInt() }
                            }
                        }
                        .groupBy { it.first }
                        .values
                        .map { b -> b.maxOf { it.second } }
                        .reduce(Int::times)
                }
        }
        .reduce(Int::plus)
)