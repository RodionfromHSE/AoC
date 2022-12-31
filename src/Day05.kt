private const val DAY = 5


data class Query(
    val amount: Int,
    val from: Int,
    val to: Int
) {
    companion object {
        private val pattern = Regex("move (\\d+) from (\\d+) to (\\d+)")
    }
    constructor(string: String) : this(
        amount = pattern.find(string)?.groupValues?.get(1)?.toInt()!!,
        from = pattern.find(string)?.groupValues?.get(2)?.toInt()!! - 1,
        to = pattern.find(string)?.groupValues?.get(3)?.toInt()!! - 1
    )
}

fun printStacks(stacks: List<MutableList<Char>>) = stacks.forEachIndexed { index, chars ->
    print("$index: ")
    chars.apply(::print)
    println()
}

fun main() {
    fun part1(stacks: MutableList<MutableList<Char>>, queries: List<Query>): String {
        for (q in queries) {
            repeat(q.amount) {
                stacks[q.to].add(stacks[q.from].removeLast())
            }
        }
        return stacks.map { it.last() }.joinToString("")
    }

    fun part2(stacks: MutableList<MutableList<Char>>, queries: List<Query>): String {
        for (q in queries) {
            stacks[q.to].addAll(stacks[q.from].takeLast(q.amount))
            repeat(q.amount) { stacks[q.from].removeLast() }
        }
        return stacks.map { it.last() }.joinToString("")
    }

    fun readStacks(lines: List<String>) : Pair<MutableList<MutableList<Char>>, List<Query>> {
        var i = 0
        var stacksInput = mutableListOf<String>()
        while (lines[i++].isNotBlank()) {
            stacksInput.add(lines[i-1])
        }

        val stacks: MutableList<MutableList<Char>> = MutableList((lines.first().length + 2) / 4) { mutableListOf() }
        for (line in stacksInput) {
            line.chunked(4).forEachIndexed { index, part -> if (part[1].isLetter()) stacks[index].add(part[1]) }
        }
        for (j in stacks.indices) {
            stacks[j].reverse()
        }

        val queries = mutableListOf<Query>()
        for (j in i..lines.size - 1) {
            queries.add(Query(lines[j]))
        }
        return Pair(stacks, queries)
    }

    val input = readInput("Day0${DAY}")
    val (stacks, queries) = readStacks(input)
    part1(stacks, queries).println()
    part2(stacks, queries).println()
}
