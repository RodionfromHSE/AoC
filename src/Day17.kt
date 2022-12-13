fun main() {
    fun part1(input: List<String>): Int {
        var elfNumber = 0
        val ans = input.groupBy {
            if (it == "")
                elfNumber += 1
            elfNumber
        }.values.map { elf ->
            elf.filter { el ->
                el != ""
            }.map {
                it.toInt()
            }
        }.maxOfOrNull { elf ->
            elf.sum()
        }
        return ans!!
    }

    fun part2(input: List<String>): Int {
        var elfNumber = 0
        val ans = input.groupBy {
            if (it == "")
                elfNumber += 1
            elfNumber
        }.values.map { elf ->
            elf.filter { el ->
                el != ""
            }.map {
                it.toInt()
            }
        }.map { elf ->
            elf.sum()
        }.sortedDescending().take(3).sum()
        return ans
    }

//     test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
