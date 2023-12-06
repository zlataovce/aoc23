import kotlin.math.max
import kotlin.math.min

fun main() = println(
    generateSequence(::readLine)
        .joinToString("\n")
        .split("\n\n")
        .let { p ->
            Pair(
                p[0].substringAfter(": ")
                    .split(' ')
                    .map(String::toLong),
                p.drop(1)
                    .map { s ->
                        s.split('\n')
                            .drop(1)
                            .map { l ->
                                l.split(' ').let { (a, b, c) ->
                                    Triple(a.toLong(), b.toLong(), c.toLong())
                                }
                            }
                    }
            )
        }
        .let { (seed, fs) ->
            seed.chunked(2)
                .map { (a, b) ->
                    fs
                        // this is so awful, why does it work
                        .fold(listOf(a to a + b)) { y, t ->
                            mutableListOf<Pair<Long, Long>>().also { l ->
                                l.addAll(
                                    t.fold(y) { r, (dst, src, len) ->
                                        r.flatMap { (x, y) ->
                                            buildList {
                                                (x to min(y, src))
                                                    .takeIf { it.second > it.first }
                                                    ?.let(::add)
                                                (max(src + len, x) to y)
                                                    .takeIf { it.second > it.first }
                                                    ?.let(::add)
                                                (max(x, src) to min(src + len, y))
                                                    .takeIf { it.second > it.first }
                                                    ?.let { l.add((it.first - src + dst) to (it.second - src + dst)) }
                                            }
                                        }
                                    }
                                )
                            }
                        }
                        .minOf { min(it.first, it.second) }
                }
        }
        .min()
)
