fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            l.substringAfter(':')
                .split(';')
                .flatMap { d ->
                    d.split(',').map { g ->
                        g.trim().split(' ')
                            .let { (a, b) -> b to a.toInt() }
                    }
                }
                .groupBy { it.first }
                .values
                .map { b -> b.maxOf { it.second } }
                .reduce(Int::times)
        }
        .reduce(Int::plus)
)