fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            l.substringAfter(':')
                .trim()
                .split("\\W+".toRegex())
                .map(String::toInt)
        }
        .toList()
        .let { (times, distances) ->
            times.foldIndexed(1) { i, acc, time ->
                acc * (0..time).fold(0) { acc1, x ->
                    acc1.inc().takeIf { (x * (time - x)) >= distances[i] } ?: acc1
                }
            }
        }
)