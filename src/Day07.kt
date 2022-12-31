private const val DAY = 7

class System(root: String) {
    var cwd = Node.Directory(root, null)

    fun processLs(nodes: List<Node>) = cwd.children.addAll(nodes)

    fun processCd(to: String) {
        cwd = if (to == "..")
            cwd.parent
        else
            cwd.children.findLast { it.name == to } as Node.Directory
    }

    sealed class Node(val name: String, private val size: Int) {
        class Directory(name: String, parent: Directory?, size: Int = -1) : Node(name, size) {
            val parent = parent ?: this
            val children = mutableListOf<Node>()
        }
        class File(name: String, size: Int) : Node(name, size)
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        TODO("not implemented")
    }

    fun part2(input: List<String>): Int {
        TODO("not implemented")
    }

    val input = readInput("Day0${DAY}")
    part1(input).println()
    part2(input).println()
}
