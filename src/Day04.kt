private const val DAY = 4

fun main() {

    fun isFullyIn(firstPair: List<Int>, secondPair: List<Int>) =
        firstPair.all { it in secondPair.first()..secondPair.last() }

    fun isIntersected(firstPair: List<Int>, secondPair: List<Int>) =
        firstPair.any { it in secondPair.first()..secondPair.last() }

    fun isContained(l: String, checker: (List<Int>, List<Int>) -> Boolean = ::isFullyIn): Boolean {
        // Split the input string on the comma character
        val pairs = l.split(",")

        // Iterate over the pairs and extract the integers
        val (first, second) = pairs.map { pair ->
            // Use a regular expression to split the pair on the dash character
            val (a, b) = Regex("-").split(pair)

            // Parse the integers using the toInt function
            listOf(a.toInt(), b.toInt())
        }
        return checker(first, second) || checker(second, first)
    }

    fun part1(input: List<String>) = input.count(::isContained)

    fun part2(input: List<String>) = input.count {isContained(it, ::isIntersected)}

    val input = readInput("Day0${DAY}")
    part1(input).println()
    part2(input).println()
}
