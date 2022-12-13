fun main() {
    fun RPSplay(pair: List<String>) : Int {
        val (a, b) = pair
        var ans = when (b) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }
        if ((a == "A" && b == "X") || (a == "B" && b == "Y") || (a == "C" && b == "Z")) {
            ans += 3
        } else if ((a == "A" && b == "Y") || (a == "B" && b == "Z") || (a == "C" && b == "X")) {
            ans += 6
        }
        return ans
    }

    fun part1(input: List<String>) = input.map {pair -> RPSplay(pair.split(' '))}.sum()

    fun RPSplay2(pair: List<String>) : Int {
        val (a, b) = pair
        var ans = when (b) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> 0
        }
        ans += when (a) {
            "A" -> when (b) {
                "X" -> 3
                "Y" -> 1
                "Z" -> 2
                else -> 0
            }
            "B" -> when (b) {
                "X" -> 1
                "Y" -> 2
                "Z" -> 3
                else -> 0
            }
            "C" -> when (b) {
                "X" -> 2
                "Y" -> 3
                "Z" -> 1
                else -> 0
            }
            else -> 0
        }
        return ans
    }

    fun part2(input: List<String>) = input.map {pair -> RPSplay(pair.split(' '))}.sum()

//     test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
