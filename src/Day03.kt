private const val DAY = 3

fun main() {
    fun priority(c: Char) = if (c.isUpperCase()) c - 'A' + 27 else c - 'a' + 1

    fun solve(l: String) : Int {
        val n = l.length
        val c = l.subSequence(0, n/2).toSet().intersect(l.subSequence(n/2, n).toSet()).first()
        return priority(c)
    }

    fun solve2(triple: List<String>) : Int {
        require(triple.size == 3)
        val intersected = triple.first().filter {c -> triple.all { c in it}}
        return priority(intersected.first())
    }

    fun part1(input: List<String>) = input.map(::solve).sum()

    fun part2(input: List<String>) = input.chunked(3)
        .map(::solve2)
        .sum()

    val input = readInput("Day0${DAY}")
    part1(input).println()
    part2(input).println()
}
