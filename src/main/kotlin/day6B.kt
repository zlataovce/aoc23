fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            l.substringAfter(':')
                .filterNot(Char::isWhitespace)
                .toLong()
        }
        .toList()
        .let { (time, distance) ->
            (0..time).fold(0) { acc1, x ->
                acc1.inc().takeIf { (x * (time - x)) >= distance } ?: acc1
            }
        }
)