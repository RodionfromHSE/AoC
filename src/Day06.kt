private const val DAY = 6



fun main() {
    fun solve(line: String, limit: Int) : Int {
        val letters = mutableListOf<Char>()
        line.forEachIndexed { i, c ->
            letters.add(c)
            if (i > limit - 1)
                letters.removeFirst()
            if (letters.toSet().size >= limit)
                return i + 1
        }
        return -1
    }

    fun part1(input: List<String>) = solve(input.first(),4)

    fun part2(input: List<String>): Int = solve(input.first(), 14)

    val input = readInput("Day0${DAY}")
    part1(input).println()
    part2(input).println()
}
