data class Num(val r: IntRange, val n: Int, var v: Boolean)

fun main() = println(
    generateSequence(::readLine)
        .map { l ->
            l to buildList {
                var previousSymbol = false
                var start = -1
                buildString {
                    ("$l.").forEachIndexed { i, c ->
                        if (c.isDigit()) {
                            if (start == -1) {
                                start = i
                            }

                            append(c)
                        } else {
                            if (start != -1) {
                                add(
                                    Num(
                                        start - 1..i,
                                        toString().toInt(),
                                        previousSymbol || !c.isDigit() && c != '.'
                                    )
                                )

                                start = -1
                                setLength(0)
                            }

                            previousSymbol = !c.isDigit() && c != '.'
                        }
                    }
                }
            }
        }
        .toList()
        .also { lst ->
            lst.forEachIndexed { li, (l, _) ->
                l.forEachIndexed { i, c ->
                    if (!c.isDigit() && c != '.') {
                        lst.getOrNull(li.dec())?.second?.forEach {
                            if (i in it.r) {
                                it.v = true
                            }
                        }
                        lst.getOrNull(li.inc())?.second?.forEach {
                            if (i in it.r) {
                                it.v = true
                            }
                        }
                    }
                }
            }
        }
        .sumOf { (_, n) -> n.sumOf { it.takeIf(Num::v)?.n ?: 0 } }
)