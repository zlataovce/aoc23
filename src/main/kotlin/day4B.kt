import kotlin.math.min

fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            l.substringAfter(": ")
                .split(" | ")
                .map { n -> n.trim().split("\\W+".toRegex()).map(String::toInt) }
                .let { (w, n) -> n.count(w::contains) } // not using intersect, because I want duplicates
        }
        .toList()
        .run {
            IntArray(size) { 1 }.also { c ->
                forEachIndexed { i, x ->
                    (i.inc()..<min(i + x.inc(), size)).forEach { y ->
                        c[y] += c[i]
                    }
                }
            }
        }
        .sum()
)