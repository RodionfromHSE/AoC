private const val DAY = 7

class System(root: String) {
    val rootDir = Node.Directory(root, null)
    var cwd = rootDir

    fun processLs(nodes: List<Node>) = cwd.children.addAll(nodes)

    fun processCd(to: String) {
        cwd = if (to == "..")
            cwd.parent
        else
            cwd.children.findLast { it.name == to } as Node.Directory
    }

    sealed class Node(val name: String, var size: Int) {
        class Directory(name: String, parent: Directory?, size: Int = -1) : Node(name, size) {
            val parent = parent ?: this
            val children = mutableListOf<Node>()
        }

        class File(name: String, size: Int) : Node(name, size)

        fun calc(): Int = when (this) {
            is File -> size
            is Directory -> {
                size = children.sumOf { it.calc() }
                size
            }
        }

        fun dirs(): MutableList<Directory> = when (this) {
            is File -> mutableListOf()
            is Directory -> children.fold(mutableListOf(this)) { acc, child -> acc.union(child.dirs()).toMutableList() }
        }
    }

    override fun toString(): String {
        val indent = "\t"
        val prefix = "|-> "
        fun nodeToStr(node: Node, level: Int) = indent.repeat(level) + prefix + node.name
        fun repr(level: Int, node: Node): String = when (node) {
            is Node.File -> nodeToStr(node, level)
            is Node.Directory ->
                node.children.fold(nodeToStr(node, level)) { acc, child -> acc + "\n" + repr(level + 1, child) }
        }
        return repr(0, rootDir)
    }

    fun minBy(predicate: (Node) -> Boolean) : Node {
        fun minByRec(cur: Node.Directory, min: Node.Directory) : Node.Directory {
            var r = min
            if (cur.size < min.size && predicate(cur)) r = cur
            cur.children.forEach { child ->
                if (child is Node.Directory) {
                    val childR = minByRec(child, r)
                    if (r.size > childR.size) r = childR
                }
            }
            return r
        }
        return minByRec(rootDir, rootDir)
    }
}


fun main() {
    fun build(input: List<String>): System {
        val systemStart = input.first()
        val system = System(systemStart.split(' ').last())
        val n = input.size
        var i = 1
        while (i < n) {
            val cmd = input[i]
            if (cmd.contains("cd")) {
                system.processCd(input[i].split(' ').last())
                ++i
            } else {
                val lsResult = mutableListOf<String>()
                ++i
                while (i < n && input[i].first() != '$') {
                    lsResult.add(input[i])
                    ++i
                }
                val nodes = lsResult.map {
                    if (it.contains("dir")) System.Node.Directory(it.split(' ').last(), system.cwd)
                    else System.Node.File(it.split(' ').last(), it.split(' ').first().toInt())
                }
                system.processLs(nodes)
            }
        }
        system.rootDir.calc()
        return system
    }

    fun part1(system: System): Int {

        return system.rootDir.dirs()
            .filter { it.size <= 100000 }
            .sumOf { it.size }
    }

    fun part2(system: System): Int {
        val neededSpace = 30000000 - (70000000 - system.rootDir.size)
        return system.minBy { it.size  > neededSpace }.size
    }

    val input = readInput("Day0${DAY}")
    val system = build(input)
    part1(system).println()
    part2(system).println()
}
