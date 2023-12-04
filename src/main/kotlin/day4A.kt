import kotlin.math.pow

fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            l.substringAfter(": ")
                .split(" | ")
                .map { n -> n.trim().split("\\W+".toRegex()).map(String::toInt) }
                .let { (w, n) -> n.count(w::contains) } // not using intersect, because I want duplicates
                .let { n -> n.takeIf { it > 1 }?.let { (2.0).pow(it.dec()).toInt() } ?: n }
        }
        .sum()
)