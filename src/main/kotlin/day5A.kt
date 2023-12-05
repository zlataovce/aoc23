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
            seed.map { x ->
                fs.fold(x) { y, t ->
                    t.firstNotNullOfOrNull { (dst, src, len) -> (y + dst - src).takeIf { src <= y && y < (src + len) } } ?: y
                }
            }
        }
        .min()
)
